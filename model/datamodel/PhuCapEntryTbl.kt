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

object PhuCapEntryTbl : Table(){
    val maPhuCap = integer("Mã phụ cấp").primaryKey()
    val maThanhVien = integer("Mã thành viên").references(ThanhVienHDQTEntryTbl.maThanhVien).nullable()
    val ngayDuocHuong = date("Ngày được thụ hưởng")
    val thongTinPhuCap = varchar("Thông tin phụ cấp", 1000)
}

fun ResultRow.toPhuCapEntry() = this[PhuCapEntryTbl.maThanhVien]?.let {
    PhuCapEntry(
            this[PhuCapEntryTbl.maPhuCap],
            it,
            this[PhuCapEntryTbl.ngayDuocHuong].toJavaLocalDate(),
            this[PhuCapEntryTbl.thongTinPhuCap]
    )
}

class PhuCapEntry(id: Int, maThanhVien: Int, ngayDuocHuong: LocalDate, thongTinPhuCap: String){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val maThanhVienProperty = SimpleIntegerProperty(maThanhVien)
    var maThanhVien by maThanhVienProperty

    val ngayDuocHuongProperty = SimpleObjectProperty<LocalDate>(ngayDuocHuong)
    var ngayDuocHuong by ngayDuocHuongProperty

    val thongTinPhuCapProperty = SimpleStringProperty(thongTinPhuCap)
    var thongTinPhuCap by thongTinPhuCapProperty
}

class PhuCapEntryModel : ItemViewModel<PhuCapEntry>() {
    val id = bind(PhuCapEntry::idProperty)
    val maThanhVien = bind(PhuCapEntry::maThanhVienProperty)
    val ngayDuocHuong = bind(PhuCapEntry::ngayDuocHuongProperty)
    val thongTinPhuCap = bind(PhuCapEntry::thongTinPhuCapProperty)
}


