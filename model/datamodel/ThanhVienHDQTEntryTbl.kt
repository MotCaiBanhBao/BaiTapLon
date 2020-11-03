package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.util.convertToSex
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

fun ResultRow.toThanhVienHDQTEntry() = this[ThanhVienHDQTEntryTbl.hoiDongQuanTri]?.let {
    ThanhVienHDQTEntry(
            this[ThanhVienHDQTEntryTbl.maThanhVien],
            this[ThanhVienHDQTEntryTbl.tenThanhVien],
            this[ThanhVienHDQTEntryTbl.soDienThoai],
            this[ThanhVienHDQTEntryTbl.soCMND],
            it
    )
}

object ThanhVienHDQTEntryTbl : Table(){
    val maThanhVien = integer("Mã thành viên").primaryKey()
    val tenThanhVien = varchar("Tên thành viên", 100)
    val soDienThoai = varchar("Số điện thoại", 50)
    val soCMND = varchar("Số CMND", 50)
    val hoiDongQuanTri = integer("Mã hội đồng quản trị").references(HoiDongQuanTriEntryTbl.maHoiDongQuanTri).nullable()
}

class ThanhVienHDQTEntry(id: Int, name: String, soDienThoai: String, soChungMinh: String, hoiDongQuantri: Int){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

    val soDienThoaiProperty = SimpleStringProperty(soDienThoai)
    var soDienThoai by soDienThoaiProperty

    val soChungMinhProperty = SimpleStringProperty(soChungMinh)
    var soChungMinh by soChungMinhProperty

    val hoiDongQuantriProperty = SimpleIntegerProperty(hoiDongQuantri)
    var hoiDongQuantri by hoiDongQuantriProperty
}

class ThanhVienHDQTEntryModel : ItemViewModel<ThanhVienHDQTEntry>() {
    val id = bind(ThanhVienHDQTEntry::idProperty)
    val name = bind(ThanhVienHDQTEntry::nameProperty)
    val soDienThoai = bind(ThanhVienHDQTEntry::soDienThoaiProperty)
    val soChungMinh = bind(ThanhVienHDQTEntry::soChungMinhProperty)
    val hoiDongQuantri = bind(ThanhVienHDQTEntry::hoiDongQuantriProperty)
}



