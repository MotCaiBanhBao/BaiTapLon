package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.model.Sex
import luongvany.k12tt.util.convertToSex
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*
import java.time.LocalDate

object LuongEntryTbl : Table(){
    val maLuong = integer("Mã lương").primaryKey()
    val bacLuong = varchar("Bậc lương", 50)
    val luongCoBan = integer("Lương cơ bản")
    val heSoLuong = varchar("Hệ số lương", 50)
}

fun ResultRow.toLuongEntry() = LuongEntry(this[LuongEntryTbl.maLuong], this[LuongEntryTbl.bacLuong], this[LuongEntryTbl.luongCoBan], this[LuongEntryTbl.heSoLuong])

class LuongEntry(id: Int, bacLuong: String, luongCoBan: Int, heSoLuong: String){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val bacLuongProperty = SimpleStringProperty(bacLuong)
    var bacLuong by bacLuongProperty

    val luongCoBanProperty = SimpleIntegerProperty(luongCoBan)
    var luongCoBan by luongCoBanProperty

    val heSoLuongProperty = SimpleStringProperty(heSoLuong)
    var heSoLuong by heSoLuongProperty
}

class LuongEntryModel : ItemViewModel<LuongEntry>() {
    val id = bind(LuongEntry::idProperty)
    val bacLuong = bind(LuongEntry::bacLuongProperty)
    val luongCoBan = bind(LuongEntry::luongCoBanProperty)
    val heSoLuong = bind(LuongEntry::heSoLuongProperty)
}

