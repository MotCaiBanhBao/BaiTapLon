package luongvany.k12tt.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.NhapHangEntry
import luongvany.k12tt.model.datamodel.NhapHangEntryModel
import luongvany.k12tt.model.datamodel.NhapHangEntryTbl
import luongvany.k12tt.model.datamodel.toNhapHangEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.*
import tornadofx.*
import java.lang.Exception

class NhapHangController : Controller(){

    var listOfItems: ObservableList<NhapHangEntryModel> = execute {
        NhapHangEntryTbl.selectAll().map {
            NhapHangEntryModel().apply {
                item = it.toNhapHangEntry()
            }
        }.asObservable()
    }

    var items: ObservableList<NhapHangEntryModel> by singleAssign()

    init {
        items = listOfItems
    }

    fun add(addItem: NhapHangEntry) {
        try {
            execute{
                NhapHangEntryTbl.insert {
                    it[maNhapHang] = addItem.id
                    it[maDoiTac] = addItem.maDoiTac
                    it[maHangHoa] = addItem.maHangHoa
                    it[maNhanVien] = addItem.maNhanVien
                    it[maHoaDon] = addItem.maHoaDon
                }
            }

            listOfItems.add(
                    NhapHangEntryModel().apply {
                        item = addItem
                    }
            )

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: NhapHangEntryModel){
        execute {
            NhapHangEntryTbl.deleteWhere {
                NhapHangEntryTbl.maNhapHang eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
    }

    fun edit(content: NhapHangEntry, indexItem: NhapHangEntryModel){
        enableConsoleLogger()
        try {
            execute {
                NhapHangEntryTbl.update({
                    NhapHangEntryTbl.maNhapHang eq indexItem.id.value
                }) {
                    it[maDoiTac] = content.maDoiTac
                    it[maHangHoa] = content.maHangHoa
                    it[maHoaDon] = content.maHoaDon
                    it[maNhanVien] = content.maNhanVien
                    it[maNhapHang] = content.id
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