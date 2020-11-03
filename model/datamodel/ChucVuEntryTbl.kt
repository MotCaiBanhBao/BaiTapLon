package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.model.datamodel.ChucVuEntryTbl.maChucVu
import luongvany.k12tt.model.datamodel.ChucVuEntryTbl.tenChucVu
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

object ChucVuEntryTbl: Table(){
    val maChucVu = integer("Mã chức vụ").primaryKey()
    val tenChucVu = varchar("Tên chức vụ", 100)
}

fun ResultRow.toChucVuEntry() = ChucVuEntry(this[maChucVu], this[tenChucVu])

class ChucVuEntry(maChucVu: Int, tenChucVu: String){
    val maChucVuProperty = SimpleIntegerProperty(maChucVu)
    var maChucVu by maChucVuProperty

    val tenChucVuProperty = SimpleStringProperty(tenChucVu)
    var tenChucVu by tenChucVuProperty
}

class ChucVuEntryModel : ItemViewModel<ChucVuEntry>() {
    val maChucVu = bind(ChucVuEntry::maChucVuProperty)
    val tenChucVu = bind(ChucVuEntry::tenChucVuProperty)
}


