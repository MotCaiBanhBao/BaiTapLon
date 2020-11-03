package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*


fun ResultRow.toDieuKhoanEntry() = DieuKhoanEntry(this[DieuKhoanEntryTbl.maDieuKhoan], this[DieuKhoanEntryTbl.noiDung])
object DieuKhoanEntryTbl: Table(){
    val maDieuKhoan = integer("Mã điều khoản").primaryKey()
    val noiDung = varchar("Nội dung", 1000)
}

class DieuKhoanEntry(id: Int, noiDung: String){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val noiDungProperty = SimpleStringProperty(noiDung)
    var noiDung by noiDungProperty
}

class DieuKhoanEntryModel : ItemViewModel<DieuKhoanEntry>() {
    val id = bind(DieuKhoanEntry::idProperty)
    val noiDung = bind(DieuKhoanEntry::noiDungProperty)
}
