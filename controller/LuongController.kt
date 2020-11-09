package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import tornadofx.*
import java.lang.Exception

class LuongController: Controller(){
    var listOfItems: ObservableList<LuongEntryModel> = execute {
        LuongEntryTbl.selectAll().map {
            LuongEntryModel().apply {
                item = it.toLuongEntry()
            }
        }.asObservable()
    }

    private val listOfMaLuong = execute {
        LuongEntryTbl.slice(LuongEntryTbl.heSoLuong, LuongEntryTbl.bacLuong, LuongEntryTbl.luongCoBan, LuongEntryTbl.maLuong).selectAll().map {
            toFormString(heSoLuong = it[LuongEntryTbl.heSoLuong], bacLuong = it[LuongEntryTbl.bacLuong], luongCoBan = it[LuongEntryTbl.luongCoBan], maLuong = it[LuongEntryTbl.maLuong])
        }.asObservable()
    }

    var listMaLuong: ObservableList<String> by singleAssign()
    var items: ObservableList<LuongEntryModel> by singleAssign()

    init {
        listOfMaLuong.add(" ")
        listOfMaLuong.add("+Add new mã lương")
        listMaLuong = listOfMaLuong
        items = listOfItems

    }

    fun convertToId(string: String): Int{
        val a = string.indexOf(" ;Hệ số lương")

        return string.substring(9, a).trim().toInt()
    }

    fun add(addItem: LuongEntry){
        try {
            execute{
                LuongEntryTbl.insert {
                    it[maLuong] = addItem.id
                    it[heSoLuong] = addItem.heSoLuong
                    it[bacLuong] = addItem.bacLuong
                    it[luongCoBan] = addItem.luongCoBan
                }
            }

            listOfItems.add(
                    LuongEntryModel().apply {
                        item = addItem
                    }
            )
            listOfMaLuong.add(if(listOfMaLuong.size==0) 0 else listOfMaLuong.size-1 ,toFormString(maLuong = addItem.id, bacLuong = addItem.bacLuong, luongCoBan = addItem.luongCoBan, heSoLuong = addItem.heSoLuong))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: LuongEntryModel){
        execute {
            LuongEntryTbl.deleteWhere {
                LuongEntryTbl.maLuong eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        listMaLuong.remove(toFormString(maLuong = model.id.value, bacLuong = model.bacLuong.value, luongCoBan = model.luongCoBan.value, heSoLuong = model.heSoLuong.value))
    }

    fun edit(content: LuongEntry, indexItem: LuongEntryModel){
        enableConsoleLogger()
        try {
            execute {
                LuongEntryTbl.update({
                    LuongEntryTbl.maLuong eq indexItem.id.value
                }) {
                    it[maLuong] = content.id
                    it[luongCoBan] = content.luongCoBan
                    it[bacLuong] = content.bacLuong
                    it[heSoLuong] = content.heSoLuong
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            listMaLuong.remove(toFormString(maLuong = indexItem.id.value, bacLuong = indexItem.bacLuong.value, luongCoBan = indexItem.luongCoBan.value, heSoLuong = indexItem.heSoLuong.value))
            listMaLuong.add(toFormString(maLuong = content.id, bacLuong = content.bacLuong, luongCoBan = content.luongCoBan, heSoLuong = content.heSoLuong))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    private fun toFormString(maLuong: Int, heSoLuong: String, bacLuong: String, luongCoBan: Int) = """Mã lương: $maLuong ;Hệ số lương: $heSoLuong ;Bậc lương: $bacLuong ;Lương cơ bản: $luongCoBan"""
}