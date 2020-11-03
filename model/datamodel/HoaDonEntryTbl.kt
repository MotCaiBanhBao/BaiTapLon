package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*
import java.time.LocalDate

fun ResultRow.toHoaDonEntry() = HoaDonEntry(this[HoaDonEntryTbl.maHoaDon], this[HoaDonEntryTbl.soTien], this[HoaDonEntryTbl.ngayLap].toJavaLocalDate())

object HoaDonEntryTbl: Table(){
    val maHoaDon = integer("Mã hóa đơn").primaryKey()
    val soTien = integer("Số tiền")
    val ngayLap = date("Ngày lập hóa đơn")
}

class HoaDonEntry(id: Int, soTien: Int, ngayLap: LocalDate){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val soTienProperty = SimpleIntegerProperty(soTien)
    var soTien by soTienProperty

    val ngayLapProperty = SimpleObjectProperty(ngayLap)
    var ngayLap by ngayLapProperty
}

class HoaDonEntryModel: ItemViewModel<HoaDonEntry>(){
    val id = bind{item?.idProperty}
    val soTien = bind{item?.soTienProperty}
    val ngayLap = bind{item?.ngayLapProperty}
}
