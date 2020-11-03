package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.DoiTacEntry
import luongvany.k12tt.model.datamodel.DoiTacEntryModel
import luongvany.k12tt.model.datamodel.DoiTacEntryTbl
import luongvany.k12tt.model.datamodel.toDoiTacEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import tornadofx.*
import java.lang.Exception

class DoiTacController : Controller(){

    var listOfItems: ObservableList<DoiTacEntryModel> = execute {
        DoiTacEntryTbl.selectAll().map {
            DoiTacEntryModel().apply {
                item = it.toDoiTacEntry()
            }
        }.asObservable()
    }

    private val idAndName = execute {
        DoiTacEntryTbl.slice(DoiTacEntryTbl.maDoiTac, DoiTacEntryTbl.tenDoiTac, DoiTacEntryTbl.diaChi).selectAll().map {
            toFormString(it[DoiTacEntryTbl.maDoiTac], it[DoiTacEntryTbl.tenDoiTac], it[DoiTacEntryTbl.diaChi])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<DoiTacEntryModel> by singleAssign()

    init {
        idAndName.add("+Add new đối tác")
        listName = idAndName
        items = listOfItems
    }

    fun add(addItem: DoiTacEntry) {
        try {
            execute{
                DoiTacEntryTbl.insert {
                    it[maDoiTac] = addItem.id
                    it[tenDoiTac] = addItem.name
                    it[diaChi] = addItem.diaChi
                }
            }

            listOfItems.add(
                    DoiTacEntryModel().apply {
                        item = addItem
                    }
            )
            idAndName.add(if(idAndName.size==0) 0 else idAndName.size-1 ,toFormString(addItem.id, addItem.name, addItem.diaChi))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: DoiTacEntryModel){
        execute {
            DoiTacEntryTbl.deleteWhere {
                DoiTacEntryTbl.maDoiTac eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndName.remove(toFormString(model.id.value, model.name.value, model.diaChi.value))
    }

    fun edit(content: DoiTacEntry, indexItem: DoiTacEntryModel){
        enableConsoleLogger()
        try {
            execute {
                DoiTacEntryTbl.update({
                    DoiTacEntryTbl.maDoiTac eq indexItem.id.value
                }) {
                   it[maDoiTac] = content.id
                    it[tenDoiTac] = content.name
                    it[diaChi] = content.diaChi
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndName.remove(toFormString(indexItem.id.value, indexItem.name.value, indexItem.diaChi.value))
            idAndName.add(toFormString(content.id, content.name, content.diaChi))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    private fun toFormString(content1: Int, content2: String?, content3: String?) = """ID: ${content1}, Tên: $content2, Địa chỉ: $content3"""
}