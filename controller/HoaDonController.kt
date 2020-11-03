package luongvany.k12tt.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.HoaDonEntry
import luongvany.k12tt.model.datamodel.HoaDonEntryModel
import luongvany.k12tt.model.datamodel.HoaDonEntryTbl
import luongvany.k12tt.model.datamodel.toHoaDonEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import tornadofx.*
import java.lang.Exception
import java.time.LocalDate

class HoaDonController : Controller(){
    var listOfItems: ObservableList<HoaDonEntryModel> = execute {
        HoaDonEntryTbl.selectAll().map {
            HoaDonEntryModel().apply {
                item = it.toHoaDonEntry()
            }
        }.asObservable()
    }
    private val idAndName = execute {
        HoaDonEntryTbl.slice(HoaDonEntryTbl.maHoaDon, HoaDonEntryTbl.ngayLap).selectAll().map {
            toFormString(it[HoaDonEntryTbl.maHoaDon], it[HoaDonEntryTbl.ngayLap].toJavaLocalDate())
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<HoaDonEntryModel> by singleAssign()

    init {
        listName = idAndName
        items = listOfItems
    }

    fun add(addItem: HoaDonEntry) {
        try {
            execute{
                HoaDonEntryTbl.insert {
                    it[maHoaDon] = addItem.id
                    it[soTien] = addItem.soTien
                    it[ngayLap] = addItem.ngayLap.toDate()
                }
            }

            listOfItems.add(
                    HoaDonEntryModel().apply {
                        item = addItem
                    }
            )
            idAndName.add(if(idAndName.size==0) 0 else idAndName.size-1 ,toFormString(addItem.id, addItem.ngayLap))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: HoaDonEntryModel){
        execute {
            HoaDonEntryTbl.deleteWhere {
                HoaDonEntryTbl.maHoaDon eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndName.remove(toFormString(model.id.value, model.ngayLap.value))
    }

    fun edit(content: HoaDonEntry, indexItem: HoaDonEntryModel){
        enableConsoleLogger()
        try {
            execute {
                HoaDonEntryTbl.update({
                    HoaDonEntryTbl.maHoaDon eq indexItem.id.value
                }) {
                   it[maHoaDon] = content.id
                    it[soTien] = content.soTien
                    it[ngayLap] = content.ngayLap.toDate()
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndName.remove(toFormString(indexItem.id.value, indexItem.ngayLap.value))
            idAndName.add(toFormString(content.id, content.ngayLap))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    private fun toFormString(content1: Int, content2: LocalDate) = """ID: ${content1}, Ngày Lập: $content2"""
}