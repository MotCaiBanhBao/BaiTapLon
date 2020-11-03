package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.DieuKhoanLaoDongEntry
import luongvany.k12tt.model.datamodel.DieuKhoanLaoDongEntryModel
import luongvany.k12tt.model.datamodel.DieuKhoanLaoDongEntryTbl
import luongvany.k12tt.model.datamodel.toDieuKhoanLaoDongEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import tornadofx.*
import java.lang.Exception
import kotlin.error

class DieuKhoanLaoDongController : Controller() {
    var listOfItems: ObservableList<DieuKhoanLaoDongEntryModel> = execute {
        DieuKhoanLaoDongEntryTbl.selectAll().map {
            DieuKhoanLaoDongEntryModel().apply {
                item = it.toDieuKhoanLaoDongEntry()
            }
        }.asObservable()
    }
    private val idAndContent = execute {
        DieuKhoanLaoDongEntryTbl.slice(DieuKhoanLaoDongEntryTbl.maDieuKhoan, DieuKhoanLaoDongEntryTbl.noiDung).selectAll().map {
            toFormString(it[DieuKhoanLaoDongEntryTbl.maDieuKhoan], it[DieuKhoanLaoDongEntryTbl.noiDung])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<DieuKhoanLaoDongEntryModel> by singleAssign()

    init {
        listName = idAndContent
        items = listOfItems
    }

    fun add(addItem: DieuKhoanLaoDongEntry){
        try {
            execute {
                DieuKhoanLaoDongEntryTbl.insert {
                    it[maDieuKhoan] = addItem.id
                    it[noiDung] = addItem.noiDung
                }
            }

            listOfItems.add(
                    DieuKhoanLaoDongEntryModel().apply {
                        item = addItem
                    }
            )
            idAndContent.add(if (idAndContent.size == 0) 0 else idAndContent.size - 1, toFormString(addItem.id, addItem.noiDung))
            information("Success")
        } catch (ex: Exception) {
            tornadofx.error(ex.stackTraceToString())
        }

    }

    fun delete(model: DieuKhoanLaoDongEntryModel) {
        execute {
            DieuKhoanLaoDongEntryTbl.deleteWhere {
                DieuKhoanLaoDongEntryTbl.maDieuKhoan eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndContent.remove(toFormString(model.id.value, model.noiDung.value))
    }

    fun edit(content: DieuKhoanLaoDongEntry, indexItem: DieuKhoanLaoDongEntryModel) {
        enableConsoleLogger()
        try {
            execute {
                DieuKhoanLaoDongEntryTbl.update({
                    DieuKhoanLaoDongEntryTbl.maDieuKhoan eq indexItem.id.value
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

            idAndContent.remove(toFormString(indexItem.id.value, indexItem.noiDung.value))
            idAndContent.add(toFormString(content.id, content.noiDung))

            information("Success")
        } catch (ex: Exception) {
            tornadofx.error(ex.stackTraceToString())
        }
    }
    private fun toFormString(content1: Int, content2: String) = """ID: ${content1}, Nội dung: $content2"""
}