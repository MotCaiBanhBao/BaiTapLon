package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.*

import tornadofx.*
import java.lang.Exception

class GioiThieuController : Controller(){

    var listOfItems: ObservableList<GioiThieuEntryModel> = execute {
        GioiThieuEntryTbl.selectAll().map {
            GioiThieuEntryModel().apply {
                item = it.toGioiThieuEntry()
            }
        }.asObservable()
    }

    var items: ObservableList<GioiThieuEntryModel> by singleAssign()

    init {
        items = listOfItems
    }

    fun add(addItem: GioiThieuEntry) {
        try {
            execute{
                GioiThieuEntryTbl.insert {
                    it[maGioiThieu] = addItem.maGioiThieu
                    it[maHangHoa] = addItem.maHangHoa
                    it[maHopDong] = addItem.maHopDong
                    it[maKhachHang] = addItem.maKhachHang
                    it[maNhanVien] = addItem.maNhanVien
                }
            }

            listOfItems.add(
                    GioiThieuEntryModel().apply {
                        item = addItem
                    }
            )

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: GioiThieuEntryModel){
        execute {
            GioiThieuEntryTbl.deleteWhere {
                GioiThieuEntryTbl.maGioiThieu eq (model.maGioiThieu.value.toInt())
            }
        }
        listOfItems.remove(model)
    }

    fun edit(content: GioiThieuEntry, indexItem: GioiThieuEntryModel){
        enableConsoleLogger()
        try {
            execute {
                GioiThieuEntryTbl.update({
                    GioiThieuEntryTbl.maGioiThieu eq indexItem.maGioiThieu.value
                }) {
                    it[maGioiThieu] = content.maGioiThieu
                    it[maHangHoa] = content.maHangHoa
                    it[maHopDong] = content.maHopDong
                    it[maKhachHang] = content.maKhachHang
                    it[maNhanVien] = content.maNhanVien
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