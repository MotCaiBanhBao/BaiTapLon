package luongvany.k12tt.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import org.jetbrains.exposed.sql.*
import tornadofx.*
import java.lang.Exception

class HangHoaController : Controller(){
    val gioiTinh = FXCollections.observableArrayList(Sex.NAM, Sex.NU, Sex.GIOITINHTHUBA)
    var listOfItems: ObservableList<HangHoaEntryModel> = execute {
        HangHoaEntryTbl.selectAll().map {
            HangHoaEntryModel().apply {
                item = it.toHangHoaEntry()
            }
        }.asObservable()
    }

    private val idAndName = execute {
        HangHoaEntryTbl.slice(HangHoaEntryTbl.ten, HangHoaEntryTbl.maHangHoa).selectAll().map {
            toFormString(it[HangHoaEntryTbl.maHangHoa], it[HangHoaEntryTbl.ten])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<HangHoaEntryModel> by singleAssign()

    init {
        idAndName.add("+Add new hàng hóa")
        listName = idAndName
        items = listOfItems
    }

    fun add(addItem: HangHoaEntry){
        try {
            execute{
                HangHoaEntryTbl.insert {
                    it[maHangHoa] = addItem.maHangHoa
                    it[ten] = addItem.ten
                    it[gioiTinh] = addItem.gioiTinh.toString()
                    it[namSinh] = addItem.namSinh.toDate()
                }
            }

            listOfItems.add(
                    HangHoaEntryModel().apply {
                        item = addItem
                    }
            )
            idAndName.add(if(idAndName.size==0) 0 else idAndName.size-1 ,toFormString(addItem.maHangHoa, addItem.ten))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: HangHoaEntryModel){
        execute {
            HangHoaEntryTbl.deleteWhere {
                HangHoaEntryTbl.maHangHoa eq (model.maHangHoa.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndName.remove(toFormString(model.maHangHoa.value, model.ten.value))
    }

    fun edit(content: HangHoaEntry, indexItem: HangHoaEntryModel){
        enableConsoleLogger()
        try {
            execute {
                HangHoaEntryTbl.update({
                    HangHoaEntryTbl.maHangHoa eq indexItem.maHangHoa.value
                }) {
                    it[maHangHoa] = content.maHangHoa
                    it[ten] = content.ten
                    it[gioiTinh] = content.gioiTinh.toString()
                    it[namSinh] = content.namSinh.toDate()
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndName.remove(toFormString(indexItem.maHangHoa.value, indexItem.ten.value))
            idAndName.add(toFormString(content.maHangHoa, content.ten))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    private fun toFormString(content1: Int, content2: String) = """ID: ${content1}, Tên: $content2"""
}