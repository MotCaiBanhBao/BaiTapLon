package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.ThanhVienHDQTEntry
import luongvany.k12tt.model.datamodel.ThanhVienHDQTEntryModel
import luongvany.k12tt.model.datamodel.ThanhVienHDQTEntryTbl
import luongvany.k12tt.model.datamodel.toThanhVienHDQTEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.*
import tornadofx.*
import java.lang.Exception

class ThanhVienHDQTController : Controller(){
    var listOfItems: ObservableList<ThanhVienHDQTEntryModel> = execute {
        ThanhVienHDQTEntryTbl.selectAll().map {
            ThanhVienHDQTEntryModel().apply {
                item = it.toThanhVienHDQTEntry()
            }
        }.asObservable()
    }

    private val idAndName = execute {
        ThanhVienHDQTEntryTbl.slice(ThanhVienHDQTEntryTbl.maThanhVien, ThanhVienHDQTEntryTbl.tenThanhVien).selectAll().map {
            toFormString(it[ThanhVienHDQTEntryTbl.maThanhVien], it[ThanhVienHDQTEntryTbl.tenThanhVien])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<ThanhVienHDQTEntryModel> by singleAssign()

    init {
        idAndName.add("+Add new thành viên")
        listName = idAndName
        items = listOfItems
    }

    fun add(addItem: ThanhVienHDQTEntry) {
        try {
            execute{
                ThanhVienHDQTEntryTbl.insert {
                    it[tenThanhVien] = addItem.name
                    it[maThanhVien] = addItem.id
                    it[hoiDongQuanTri] = addItem.hoiDongQuantri
                    it[soCMND] = addItem.soChungMinh
                    it[soDienThoai] = addItem.soDienThoai
                }
            }

            listOfItems.add(
                    ThanhVienHDQTEntryModel().apply {
                        item = addItem
                    }
            )

            idAndName.add(if(idAndName.size==0) 0 else idAndName.size-1 ,toFormString(addItem.id, addItem.name))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }

    }

    fun edit(content: ThanhVienHDQTEntry, indexItem: ThanhVienHDQTEntryModel){
        enableConsoleLogger()
        try {
            execute {
                ThanhVienHDQTEntryTbl.update({
                    ThanhVienHDQTEntryTbl.maThanhVien eq indexItem.id.value
                }) {
                    it[maThanhVien] = content.id
                    it[tenThanhVien] = content.name
                    it[soDienThoai] = content.soDienThoai
                    it[soCMND] = content.soChungMinh
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndName.remove(toFormString(indexItem.id.value, indexItem.name.value))
            idAndName.add(toFormString(content.id, content.name))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    private fun toFormString(content1: Int, content2: String) = """ID: ${content1}, Tên: $content2"""
}