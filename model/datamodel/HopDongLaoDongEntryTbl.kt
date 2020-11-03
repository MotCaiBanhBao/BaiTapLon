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

object HopDongLaoDongEntryTbl : Table(){
    val maHopDong = integer("Mã hợp đồng").primaryKey()
    val ngayThanhLap = date("Ngày thành lập")
}
fun ResultRow.toHopDongLaoDongEntry() = HopDongLaoDongEntry(
            this[HopDongLaoDongEntryTbl.maHopDong],
            this[HopDongLaoDongEntryTbl.ngayThanhLap].toJavaLocalDate()
    )

class HopDongLaoDongEntry(id: Int, ngayThanhLap: LocalDate){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val ngayThanhLapProperty = SimpleObjectProperty<LocalDate>(ngayThanhLap)
    var ngayThanhLap by ngayThanhLapProperty
}

class HopDongLaoDongEntryModel : ItemViewModel<HopDongLaoDongEntry>() {
    val id = bind(HopDongLaoDongEntry::idProperty)
    val ngayThanhLap = bind(HopDongLaoDongEntry::ngayThanhLapProperty)
}

