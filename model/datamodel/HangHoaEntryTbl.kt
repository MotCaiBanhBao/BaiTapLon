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

fun ResultRow.toHangHoaEntry() = HangHoaEntry(
        this[HangHoaEntryTbl.maHangHoa], this[HangHoaEntryTbl.ten],
        this[HangHoaEntryTbl.gioiTinh].convertToSex(),
        this[HangHoaEntryTbl.namSinh].toJavaLocalDate())

object HangHoaEntryTbl: Table(){
    val maHangHoa = integer("Mã hàng hóa").primaryKey()
    val ten = varchar("Tên", 100)
    val gioiTinh = varchar("Giới tính", 10)
    val namSinh = date("date")
}

class HangHoaEntry(maHangHoa: Int, ten: String, gioiTinh: Sex, namSinh: LocalDate){
    var maHangHoaProperty = SimpleIntegerProperty(maHangHoa)
    val maHangHoa by maHangHoaProperty

    var tenProperty = SimpleStringProperty(ten)
    val ten by tenProperty

    var gioiTinhProperty = SimpleObjectProperty(gioiTinh)
    val gioiTinh by gioiTinhProperty

    var namSinhProperty = SimpleObjectProperty(namSinh)
    val namSinh by namSinhProperty
}

class HangHoaEntryModel : ItemViewModel<HangHoaEntry>() {
    val maHangHoa = bind{item?.maHangHoaProperty}
    val ten = bind{item?.tenProperty}
    val gioiTinh = bind{item?.gioiTinhProperty}
    val namSinh = bind{item?.namSinhProperty}
}