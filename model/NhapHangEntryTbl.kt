package luongvany.k12tt.model

import org.jetbrains.exposed.sql.Table

object NhapHangEntryTbl: Table() {
    val maNhapHang = integer("Mã nhập hàng").autoIncrement().primaryKey()
    val maNhanVien = integer("Nhân viên nhập hàng").references(StaffEntryTbl.id).nullable()
    val maHoaDon = integer("Mã hóa đơn").references(HoaDonEntryTbl.maHoaDon).nullable()
    val maHangHoa = integer("Mã hàng hóa").references(HangHoaEntryTbl.maHangHoa).nullable()
    val maDoiTac = integer("Mã đối tác").references(DoiTacEntryTbl.maDoiTac).nullable()
}

