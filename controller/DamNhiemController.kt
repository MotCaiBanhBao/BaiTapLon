package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.DamNhiemEntry
import luongvany.k12tt.model.datamodel.DamNhiemEntryModel
import luongvany.k12tt.model.datamodel.DamNhiemEntryTbl
import luongvany.k12tt.model.datamodel.toDamNhiemEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import tornadofx.*
import java.lang.Exception

class DamNhiemController : Controller(){
    var listOfItems: ObservableList<DamNhiemEntryModel> = execute {
        DamNhiemEntryTbl.selectAll().map {
            DamNhiemEntryModel().apply {
                item = it.toDamNhiemEntry()
            }
        }.asObservable()
    }

    var items: ObservableList<DamNhiemEntryModel> by singleAssign()

    init {
        items = listOfItems
    }

    fun add(addItem: DamNhiemEntry){
        try {
            execute{
                DamNhiemEntryTbl.insert {
                    it[maChucVu] = addItem.maChucVu
                    it[maHopDong] = addItem.maHopDong
                    it[maNhanVien] = addItem.maNhanVien
                }
            }

            listOfItems.add(
                    DamNhiemEntryModel().apply {
                        item = addItem
                    }
            )

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: DamNhiemEntryModel){
        execute {
            DamNhiemEntryTbl.deleteWhere {
                DamNhiemEntryTbl.maChucVu eq (model.maChucVu.value.toInt())
                DamNhiemEntryTbl.maHopDong eq (model.maHopDong.value.toInt())
                DamNhiemEntryTbl.maNhanVien eq (model.maNhanVien.value.toInt())
            }
        }
        listOfItems.remove(model)
    }

    fun edit(content: DamNhiemEntry, indexItem: DamNhiemEntryModel){
        enableConsoleLogger()
        try {
            execute {
                DamNhiemEntryTbl.update({
                    DamNhiemEntryTbl.maChucVu eq (content.maChucVu) and
                            (DamNhiemEntryTbl.maHopDong eq (content.maHopDong)) and
                            (DamNhiemEntryTbl.maNhanVien eq (content.maNhanVien))
                }) {
                    it[maChucVu] = content.maChucVu
                    it[maHopDong] = content.maHopDong
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