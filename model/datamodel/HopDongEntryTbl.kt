package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*
import java.time.LocalDate



fun ResultRow.toHopDongEntry() = HopDongEntry(this[HopDongEntryTbl.maHopDong], this[HopDongEntryTbl.thoiGianThucHien].toJavaLocalDate())
object HopDongEntryTbl : Table(){
    val maHopDong = integer("Mã hợp đồng").primaryKey()
    val thoiGianThucHien = date("Hạn thời gian thực hiện")
}

class HopDongEntry(id: Int, thoiGianThucHien: LocalDate){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val thoiGianThucHienProperty = SimpleObjectProperty(thoiGianThucHien)
    var thoiGianThucHien by thoiGianThucHienProperty
}

class HopDongEntryModel : ItemViewModel<HopDongEntry>() {
    val id = bind(HopDongEntry::idProperty)
    val thoiGianThucHien = bind(HopDongEntry::thoiGianThucHienProperty)
}

