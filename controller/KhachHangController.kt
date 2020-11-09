package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.KhachHangEntry
import luongvany.k12tt.model.datamodel.KhachHangEntryModel
import luongvany.k12tt.model.datamodel.KhachHangEntryTbl
import luongvany.k12tt.model.datamodel.toKhachHangEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import tornadofx.*

class KhachHangController : Controller(){
    var listOfItems: ObservableList<KhachHangEntryModel> = execute {
        KhachHangEntryTbl.selectAll().map {
            KhachHangEntryModel().apply {
                item = it.toKhachHangEntry()
            }
        }.asObservable()
    }
    private val idAndName = execute {
        KhachHangEntryTbl.slice(KhachHangEntryTbl.tenKhachHang, KhachHangEntryTbl.maKhachHang).selectAll().map {
            toFormString(it[KhachHangEntryTbl.maKhachHang], it[KhachHangEntryTbl.tenKhachHang])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<KhachHangEntryModel> by singleAssign()

    init {
        idAndName.add(" ")
        idAndName.add("+Add new Khách hàng")
        listName = idAndName
        items = listOfItems
    }

    fun add(addItem: KhachHangEntry) {
        try {
            execute{
                KhachHangEntryTbl.insert {
                    it[maKhachHang] = addItem.id
                    it[tenKhachHang] = addItem.tenKhachHang
                    it[diaChi] = addItem.diaChi
                    it[soThich] = addItem.soThich
                    it[soDienThoai] = addItem.soDienThoai
                }
            }

            listOfItems.add(
                    KhachHangEntryModel().apply {
                        item = addItem
                    }
            )
            idAndName.add(if(idAndName.size==0) 0 else idAndName.size-1 ,toFormString(addItem.id, addItem.tenKhachHang))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: KhachHangEntryModel){
        execute {
            KhachHangEntryTbl.deleteWhere {
                KhachHangEntryTbl.maKhachHang eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndName.remove(toFormString(model.id.value, model.tenKhachHang.value))
    }

    fun edit(content: KhachHangEntry, indexItem: KhachHangEntryModel){
        enableConsoleLogger()
        try {
            execute {
                KhachHangEntryTbl.update({
                    KhachHangEntryTbl.maKhachHang eq indexItem.id.value
                }) {
                    it[maKhachHang] = content.id
                    it[tenKhachHang] = content.tenKhachHang
                    it[diaChi] = content.diaChi
                    it[soThich] = content.soThich
                    it[soDienThoai] = content.soDienThoai
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndName.remove(toFormString(indexItem.id.value, indexItem.tenKhachHang.value))
            idAndName.add(toFormString(content.id, content.tenKhachHang))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    private fun toFormString(content1: Int, content2: String) = """ID: ${content1}, Tên: $content2"""
}