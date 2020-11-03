package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

object GioiThieuEntryTbl: Table(){
    val maGioiThieu = integer("Mã giới thiệu").primaryKey()
    val maNhanVien = integer("Mã nhân viên").references(StaffEntryTbl.id)
    val maHopDong = integer("Mã hợp đồng lao động").references(HopDongEntryTbl.maHopDong)
    val maHangHoa = integer("Mã hàng hóa").references(HangHoaEntryTbl.maHangHoa)
    val maKhachHang = integer("Mã khách hàng").references(KhachHangEntryTbl.maKhachHang)
}

fun ResultRow.toGioiThieuEntry() = GioiThieuEntry(this[GioiThieuEntryTbl.maGioiThieu], this[GioiThieuEntryTbl.maNhanVien],
        this[GioiThieuEntryTbl.maHopDong], this[GioiThieuEntryTbl.maHangHoa], this[GioiThieuEntryTbl.maKhachHang])

class GioiThieuEntry(maGioiThieu: Int, maNhanVien: Int, maHopDong: Int, maHangHoa: Int, maKhachHang: Int){
    val maGioiThieuProperty = SimpleIntegerProperty(maGioiThieu)
    var maGioiThieu by maGioiThieuProperty

    val maNhanVienProperty = SimpleIntegerProperty(maNhanVien)
    var maNhanVien by maNhanVienProperty

    val maHopDongProperty = SimpleIntegerProperty(maHopDong)
    var maHopDong by maHopDongProperty

    val maHangHoaProperty = SimpleIntegerProperty(maHangHoa)
    var maHangHoa by maHangHoaProperty

    val maKhachHangProperty = SimpleIntegerProperty(maKhachHang)
    var maKhachHang by maKhachHangProperty
}

class GioiThieuEntryModel : ItemViewModel<GioiThieuEntry>() {
    val maGioiThieu = bind(GioiThieuEntry::maGioiThieuProperty)
    val maNhanVien = bind(GioiThieuEntry::maNhanVienProperty)
    val maHopDong = bind(GioiThieuEntry::maHopDongProperty)
    val maHangHoa = bind(GioiThieuEntry::maHangHoaProperty)
    val maKhachHang = bind(GioiThieuEntry::maKhachHangProperty)
}


