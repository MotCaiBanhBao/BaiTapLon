package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.*
import tornadofx.*
import java.time.LocalDate

class HopDongLaoDongController : Controller(){
    var listOfItems: ObservableList<HopDongLaoDongEntryModel> = execute {
        HopDongLaoDongEntryTbl.selectAll().map {
            HopDongLaoDongEntryModel().apply {
                item = it.toHopDongLaoDongEntry()
            }
        }.asObservable()
    }
    private val idAndNgayThanhLap = execute {
        HopDongLaoDongEntryTbl.slice(HopDongLaoDongEntryTbl.maHopDong, HopDongLaoDongEntryTbl.ngayThanhLap).selectAll().map {
            toFormString(it[HopDongLaoDongEntryTbl.maHopDong], it[HopDongLaoDongEntryTbl.ngayThanhLap].toJavaLocalDate())
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<HopDongLaoDongEntryModel> by singleAssign()

    init {
        listName = idAndNgayThanhLap
        items = listOfItems
    }

    fun add(addItem: HopDongLaoDongEntry){
        try {
            execute{
                HopDongLaoDongEntryTbl.insert {
                    it[maHopDong] = addItem.id
                    it[ngayThanhLap] = addItem.ngayThanhLap.toDate()
                }
            }

            listOfItems.add(
                    HopDongLaoDongEntryModel().apply {
                        item = addItem
                    }
            )
            idAndNgayThanhLap.add(if(idAndNgayThanhLap.size==0) 0 else idAndNgayThanhLap.size-1 ,toFormString(addItem.id, addItem.ngayThanhLap))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: HopDongLaoDongEntryModel){
        execute {
            HopDongLaoDongEntryTbl.deleteWhere {
                HopDongLaoDongEntryTbl.maHopDong eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndNgayThanhLap.remove(toFormString(model.id.value, model.ngayThanhLap.value))
    }

    fun edit(content: HopDongLaoDongEntry, indexItem: HopDongLaoDongEntryModel){
        enableConsoleLogger()
        try {
            execute {
                HopDongLaoDongEntryTbl.update({
                    HopDongLaoDongEntryTbl.maHopDong eq indexItem.id.value
                }) {
                    it[maHopDong] = content.id
                    it[ngayThanhLap] = content.ngayThanhLap.toDate()
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndNgayThanhLap.remove(toFormString(indexItem.id.value, indexItem.ngayThanhLap.value))
            idAndNgayThanhLap.add(toFormString(content.id, content.ngayThanhLap))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }
    private fun toFormString(content1: Int, content2: LocalDate) = """ID: ${content1}, Ngày thành lập: $content2"""
}