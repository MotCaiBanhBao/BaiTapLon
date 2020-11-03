package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

object DieuKhoanLaoDongEntryTbl: Table(){
    val maDieuKhoan = integer("Mã điều khoản").primaryKey()
    val noiDung = varchar("Nội dung", 1000)
}

fun ResultRow.toDieuKhoanLaoDongEntry() = DieuKhoanLaoDongEntry(
    this[DieuKhoanLaoDongEntryTbl.maDieuKhoan],
    this[DieuKhoanLaoDongEntryTbl.noiDung]
)

class DieuKhoanLaoDongEntry(id: Int, noiDung: String){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val noiDungProperty = SimpleStringProperty(noiDung)
    var noiDung by noiDungProperty
}

class DieuKhoanLaoDongEntryModel : ItemViewModel<DieuKhoanLaoDongEntry>() {
    val id = bind(DieuKhoanLaoDongEntry::idProperty)
    val noiDung = bind(DieuKhoanLaoDongEntry::noiDungProperty)
}

