package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

fun ResultRow.toDoiTacEntry() = this[DoiTacEntryTbl.tenDoiTac]?.let { this[DoiTacEntryTbl.diaChi]?.let { it1 -> DoiTacEntry(this[DoiTacEntryTbl.maDoiTac], it, it1) } }

object DoiTacEntryTbl: Table(){
    val maDoiTac = integer("Mã đối tác").primaryKey()
    val tenDoiTac = varchar("Tên đối tác", 100).nullable()
    val diaChi = varchar("Địa chỉ", 50).nullable()
}

class DoiTacEntry(maDoiTac: Int, tenDoiTac: String, diaChi: String){
    val maDoiTacProperty = SimpleIntegerProperty(maDoiTac)
    var id by maDoiTacProperty
    val tenDoiTacProperty = SimpleStringProperty(tenDoiTac)
    var name by tenDoiTacProperty
    val diaChiProperty = SimpleStringProperty(diaChi)
    var diaChi by diaChiProperty
}

class DoiTacEntryModel: ItemViewModel<DoiTacEntry>(){
    val id = bind{item?.maDoiTacProperty}
    val name = bind{item?.tenDoiTacProperty}
    val diaChi = bind{item?.diaChiProperty}
}