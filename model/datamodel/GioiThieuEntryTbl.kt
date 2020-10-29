package luongvany.k12tt.model.datamodel

import org.jetbrains.exposed.sql.Table

object GioiThieuEntryTbl: Table(){
    val maGioiThieu = integer("Mã giới thiệu").primaryKey()
    val maNhanVien = integer("Mã nhân viên").references(StaffEntryTbl.id)
    val maHopDong = integer("Mã hợp đồng lao động").references(HopDongEntryTbl.maHopDong)
    val maHangHoa = integer("Mã hàng hóa").references(HangHoaEntryTbl.maHangHoa)
    val maKhachHang = integer("Mã khách hàng").references(KhachHangEntryTbl.maKhachHang)
}