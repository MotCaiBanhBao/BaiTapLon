package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.ChucVuEntry
import luongvany.k12tt.model.datamodel.ChucVuEntryModel
import luongvany.k12tt.model.datamodel.ChucVuEntryTbl
import luongvany.k12tt.model.datamodel.toChucVuEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import tornadofx.*

class ChucVuController: Controller(){

    var listOfItems: ObservableList<ChucVuEntryModel> = execute {
        ChucVuEntryTbl.selectAll().map {
            ChucVuEntryModel().apply {
                item = it.toChucVuEntry()
            }
        }.asObservable()
    }

    private val idAndName = execute {
        ChucVuEntryTbl.slice(ChucVuEntryTbl.maChucVu, ChucVuEntryTbl.tenChucVu).selectAll().map {
            toFormString(it[ChucVuEntryTbl.maChucVu], it[ChucVuEntryTbl.tenChucVu])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<ChucVuEntryModel> by singleAssign()

    init {
        listName = idAndName
        items = listOfItems
    }

    fun add(addItem: ChucVuEntry){
        try {
            execute{
                ChucVuEntryTbl.insert {
                    it[maChucVu] = addItem.maChucVu
                    it[tenChucVu] = addItem.tenChucVu
                }
            }

            listOfItems.add(
                    ChucVuEntryModel().apply {
                        item = addItem
                    }
            )
            idAndName.add(if(idAndName.size==0) 0 else idAndName.size-1 ,toFormString(addItem.maChucVu, addItem.tenChucVu))
            information("Success")
        }catch (ex: Exception){
            tornadofx.error(ex.stackTraceToString())
        }

    }

    fun delete(model: ChucVuEntryModel){
        execute {
            ChucVuEntryTbl.deleteWhere{
                ChucVuEntryTbl.maChucVu eq (model.maChucVu.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndName.remove(toFormString(model.maChucVu.value, model.tenChucVu.value))
    }

    fun edit(content: ChucVuEntry, indexItem: ChucVuEntryModel){
        enableConsoleLogger()
        try {
            execute {
                ChucVuEntryTbl.update({
                    ChucVuEntryTbl.maChucVu eq indexItem.maChucVu.value
                }) {
                    it[maChucVu] = content.maChucVu
                    it[tenChucVu] = content.tenChucVu
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndName.remove(toFormString(indexItem.maChucVu.value, indexItem.tenChucVu.value))
            idAndName.add(toFormString(content.maChucVu, content.tenChucVu))

            information("Success")
        }catch (ex: Exception){
            tornadofx.error(ex.stackTraceToString())
        }
    }

    private fun toFormString(content1: Int, content2: String) = """ID: ${content1}, Tên: $content2"""
}