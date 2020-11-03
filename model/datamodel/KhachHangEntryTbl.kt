package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

fun ResultRow.toKhachHangEntry() = KhachHangEntry(this[KhachHangEntryTbl.maKhachHang],
        this[KhachHangEntryTbl.diaChi], this[KhachHangEntryTbl.tenKhachHang], this[KhachHangEntryTbl.soThich],
        this[KhachHangEntryTbl.soDienThoai])

object KhachHangEntryTbl: Table(){
    val maKhachHang = integer("Mã khách hàng").primaryKey()
    val diaChi = varchar("Địa chỉ", 100)
    val tenKhachHang = varchar("Tên khách hàng", 100)
    val soThich = varchar("Sở thích cá nhân", 1000)
    val soDienThoai = varchar("Số điện thoại", 20)
}

class KhachHangEntry(maKhachHang: Int, diaChi: String, tenKhachHang: String, soThich: String, soDienThoai: String){
    val idProperty = SimpleIntegerProperty(maKhachHang)
    var id by idProperty

    val diaChiProperty = SimpleStringProperty(diaChi)
    var diaChi by diaChiProperty

    val tenKhachHangProperty = SimpleStringProperty(tenKhachHang)
    var tenKhachHang by tenKhachHangProperty

    val soThichProperty = SimpleStringProperty(soThich)
    var soThich by soThichProperty

    val soDienThoaiProperty = SimpleStringProperty(soDienThoai)
    var soDienThoai by soDienThoaiProperty
}

class KhachHangEntryModel : ItemViewModel<KhachHangEntry>() {
    val id = bind(KhachHangEntry::idProperty)
    val diaChi = bind(KhachHangEntry::diaChiProperty)
    val tenKhachHang = bind(KhachHangEntry::tenKhachHangProperty)
    val soThich = bind(KhachHangEntry::soThichProperty)
    val soDienThoai = bind(KhachHangEntry::soDienThoaiProperty)
}
