package luongvany.k12tt.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.DieuKhoanEntry
import luongvany.k12tt.model.datamodel.DieuKhoanEntryModel
import luongvany.k12tt.model.datamodel.DieuKhoanEntryTbl
import luongvany.k12tt.model.datamodel.toDieuKhoanEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import tornadofx.*
import java.lang.Exception

class DieuKhoanController : Controller(){

    var listOfItems: ObservableList<DieuKhoanEntryModel> = execute {
        DieuKhoanEntryTbl.selectAll().map {
            DieuKhoanEntryModel().apply {
                item = it.toDieuKhoanEntry()
            }
        }.asObservable()
    }
    private val idAndName = execute {
        DieuKhoanEntryTbl.slice(DieuKhoanEntryTbl.maDieuKhoan, DieuKhoanEntryTbl.noiDung).selectAll().map {
            toFormString(it[DieuKhoanEntryTbl.maDieuKhoan], it[DieuKhoanEntryTbl.noiDung])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<DieuKhoanEntryModel> by singleAssign()

    init {
        idAndName.add("+Add điều khoản lao động")
        listName = idAndName
        items = listOfItems
    }

    fun add(addItem: DieuKhoanEntry) {
        try {
            execute{
                DieuKhoanEntryTbl.insert {
                    it[maDieuKhoan] = addItem.id
                    it[noiDung] = addItem.noiDung
                }
            }

            listOfItems.add(
                    DieuKhoanEntryModel().apply {
                        item = addItem
                    }
            )
            idAndName.add(if(idAndName.size==0) 0 else idAndName.size-1 ,toFormString(addItem.id, addItem.noiDung))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: DieuKhoanEntryModel){
        execute {
            DieuKhoanEntryTbl.deleteWhere {
                DieuKhoanEntryTbl.maDieuKhoan eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndName.remove(toFormString(model.id.value, model.noiDung.value))
    }

    fun edit(content: DieuKhoanEntry, indexItem: DieuKhoanEntryModel){
        enableConsoleLogger()
        try {
            execute {
                DieuKhoanEntryTbl.update({
                    DieuKhoanEntryTbl.maDieuKhoan eq indexItem.id.value
                }) {
                    it[maDieuKhoan] = content.id
                    it[noiDung] = content.noiDung
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndName.remove(toFormString(indexItem.id.value, indexItem.noiDung.value))
            idAndName.add(toFormString(content.id, content.noiDung))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    private fun toFormString(content1: Int, content2: String) = """ID: ${content1}, Điều khoản: $content2"""
}