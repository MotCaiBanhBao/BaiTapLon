package luongvany.k12tt.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.HopDongEntry
import luongvany.k12tt.model.datamodel.HopDongEntryModel
import luongvany.k12tt.model.datamodel.HopDongEntryTbl
import luongvany.k12tt.model.datamodel.toHopDongEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import tornadofx.*
import java.lang.Exception
import java.time.LocalDate

class HopDongController : Controller(){
    var listOfItems: ObservableList<HopDongEntryModel> = execute {
        HopDongEntryTbl.selectAll().map {
            HopDongEntryModel().apply {
                item = it.toHopDongEntry()
            }
        }.asObservable()
    }

    private val idAndThoiGian = execute {
        HopDongEntryTbl.slice(HopDongEntryTbl.thoiGianThucHien, HopDongEntryTbl.maHopDong).selectAll().map {
            toFormString(it[HopDongEntryTbl.maHopDong], it[HopDongEntryTbl.thoiGianThucHien].toJavaLocalDate())
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<HopDongEntryModel> by singleAssign()

    init {
        idAndThoiGian.add(" ")
        idAndThoiGian.add("+Add new hợp đồng")
        listName = idAndThoiGian
        items = listOfItems
    }

    fun add(addItem: HopDongEntry){
        try {
            execute{
                HopDongEntryTbl.insert {
                    it[maHopDong] = addItem.id
                    it[thoiGianThucHien] = addItem.thoiGianThucHien.toDate()
                }
            }

            listOfItems.add(
                    HopDongEntryModel().apply {
                        item = addItem
                    }
            )
            idAndThoiGian.add(if(idAndThoiGian.size==0) 0 else idAndThoiGian.size-1 ,toFormString(addItem.id, addItem.thoiGianThucHien))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }

    }

    fun delete(model: HopDongEntryModel){
        execute {
            HopDongEntryTbl.deleteWhere {
                HopDongEntryTbl.maHopDong eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndThoiGian.remove(toFormString(model.id.value, model.thoiGianThucHien.value))
    }

    fun edit(content: HopDongEntry, indexItem: HopDongEntryModel){
        enableConsoleLogger()
        try {
            execute {
                HopDongEntryTbl.update({
                    HopDongEntryTbl.maHopDong eq indexItem.id.value
                }) {
                    it[maHopDong] = content.id
                    it[thoiGianThucHien] = content.thoiGianThucHien.toDate()
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndThoiGian.remove(toFormString(indexItem.id.value, indexItem.thoiGianThucHien.value))
            idAndThoiGian.add(toFormString(content.id, content.thoiGianThucHien))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    private fun toFormString(content1: Int, content2: LocalDate) = """ID: ${content1}, Thòi gian thực hiện: $content2"""
}