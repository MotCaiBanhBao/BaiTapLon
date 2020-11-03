package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import luongvany.k12tt.util.toLocalDate
import org.jetbrains.exposed.sql.*
import tornadofx.*
import java.lang.Exception
import java.time.LocalDate

class HDQTController : Controller(){
    var listOfItems: ObservableList<HoiDongQuanTriEntryModel> = execute {
        HoiDongQuanTriEntryTbl.selectAll().map {
            HoiDongQuanTriEntryModel().apply {
                item = it.toHDQTEntry()
            }
        }.asObservable()
    }
    private val idAndNhiemKy = execute {
        HoiDongQuanTriEntryTbl.slice(HoiDongQuanTriEntryTbl.maHoiDongQuanTri, HoiDongQuanTriEntryTbl.nhiemKy).selectAll().map {
            toFormString(it[HoiDongQuanTriEntryTbl.maHoiDongQuanTri], it[HoiDongQuanTriEntryTbl.nhiemKy])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<HoiDongQuanTriEntryModel> by singleAssign()

    init {
        idAndNhiemKy.add("+Add hội đồng quản trị")
        listName = idAndNhiemKy
        items = listOfItems
    }

    fun add(addItem: HoiDongQuanTriEntry) {
        try {
            execute{
                HoiDongQuanTriEntryTbl.insert {
                    it[maHoiDongQuanTri] = addItem.maHoiDongQuanTri
                    it[nhiemKy] = addItem.nhiemKy
                }
            }

            listOfItems.add(
                    HoiDongQuanTriEntryModel().apply {
                        item = addItem
                    }
            )
            
            idAndNhiemKy.add(if(idAndNhiemKy.size==0) 0 else idAndNhiemKy.size-1 ,toFormString(addItem.maHoiDongQuanTri, addItem.nhiemKy))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: HoiDongQuanTriEntryModel){
        execute {
            StaffEntryTbl.deleteWhere {
                HoiDongQuanTriEntryTbl.maHoiDongQuanTri eq (model.maHoiDongQuanTri.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndNhiemKy.remove(toFormString(model.maHoiDongQuanTri.value, model.nhiemKy.value))
    }

    fun edit(content: HoiDongQuanTriEntry, indexItem: HoiDongQuanTriEntryModel){
        enableConsoleLogger()
        try {
            execute {
                HoiDongQuanTriEntryTbl.update({
                    HoiDongQuanTriEntryTbl.maHoiDongQuanTri eq indexItem.maHoiDongQuanTri.value
                }) {
                    it[maHoiDongQuanTri] = content.maHoiDongQuanTri
                    it[nhiemKy] = content.nhiemKy
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndNhiemKy.remove(toFormString(indexItem.maHoiDongQuanTri.value, indexItem.nhiemKy.value))
            idAndNhiemKy.add(toFormString(content.maHoiDongQuanTri, content.nhiemKy))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }
    fun toLocalDate(content: String): Pair<LocalDate, LocalDate>{
        val index = content.indexOf("đến")
        val string1 = content.substring(2, index).trim()
        val string2 = content.substring(index+3, content.length).trim()

        return string1.toLocalDate() to string2.toLocalDate()
    }

    fun toNhiemKyString(content1: LocalDate, content2: LocalDate) = """Từ $content1 đến $content2"""
    private fun toFormString(content1: Int, content2: String) = """ID: ${content1}, Nhiệm kỳ: $content2"""
}