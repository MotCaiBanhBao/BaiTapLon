package luongvany.k12tt.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.sql.Table
import tornadofx.*
import java.time.LocalDate

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

class HangHoaEntryModel: ItemViewModel<HangHoaEntry>(){
    val id = bind{item?.maHangHoaProperty}
    val ten = bind{item?.tenProperty}
    val gioiTinh = bind{item?.gioiTinhProperty}
    val namSinh = bind{item?.namSinhProperty}
}