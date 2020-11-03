package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import org.jetbrains.exposed.sql.*
import tornadofx.*
import java.lang.Exception

class PhuCapController : Controller(){
    var listOfItems: ObservableList<PhuCapEntryModel> = execute {
        PhuCapEntryTbl.selectAll().map {
            PhuCapEntryModel().apply {
                item = it.toPhuCapEntry()
            }
        }.asObservable()
    }

    var items: ObservableList<PhuCapEntryModel> by singleAssign()

    init {
        items = listOfItems
    }

    fun add(addItem: PhuCapEntry){
        try {
            execute{
                PhuCapEntryTbl.insert {
                   it[maPhuCap] = addItem.id
                    it[maThanhVien] = addItem.maThanhVien
                    it[ngayDuocHuong] = addItem.ngayDuocHuong.toDate()
                    it[thongTinPhuCap] = addItem.thongTinPhuCap
                }
            }

            listOfItems.add(
                    PhuCapEntryModel().apply {
                        item = addItem
                    }
            )

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: PhuCapEntryModel){
        execute {
            PhuCapEntryTbl.deleteWhere {
                PhuCapEntryTbl.maPhuCap eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
    }

    fun edit(content: PhuCapEntry, indexItem: PhuCapEntryModel){
        enableConsoleLogger()
        try {
            execute {
                PhuCapEntryTbl.update({
                    PhuCapEntryTbl.maPhuCap eq indexItem.id.value
                }) {
                    it[maPhuCap] = content.id
                    it[maThanhVien] = content.maThanhVien
                    it[ngayDuocHuong] = content.ngayDuocHuong.toDate()
                    it[thongTinPhuCap] = content.thongTinPhuCap
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }
}