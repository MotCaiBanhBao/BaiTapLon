package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.LuongEntryTbl
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.selectAll
import tornadofx.*

class LuongController: Controller(){
    private val listOfMaLuong = execute {
        LuongEntryTbl.slice(LuongEntryTbl.heSoLuong, LuongEntryTbl.bacLuong, LuongEntryTbl.luongCoBan, LuongEntryTbl.maLuong).selectAll().map {
            "Mã lương: " + it[LuongEntryTbl.maLuong] +
                    " ;Hệ số lương: " + it[LuongEntryTbl.heSoLuong] +
                    " ;Bậc lương: " + it[LuongEntryTbl.bacLuong] +
                    " ;Lương cơ bản: " + it[LuongEntryTbl.luongCoBan]
        }.asObservable()
    }

    var listMaLuong: ObservableList<String> by singleAssign()

    init {
        listMaLuong = listOfMaLuong
    }

    fun convertToId(string: String): Int{
        val a = string.indexOf(" ;Hệ số lương")

        return string.substring(9, a).trim().toInt()
    }
}