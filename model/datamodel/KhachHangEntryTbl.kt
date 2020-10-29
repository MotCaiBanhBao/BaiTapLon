package luongvany.k12tt.model.datamodel

import org.jetbrains.exposed.sql.Table

object KhachHangEntryTbl: Table(){
    val maKhachHang = integer("Mã khách hàng").primaryKey()
    val diaChi = varchar("Địa chỉ", 100)
    val tenKhachHang = varchar("Tên khách hàng", 100)
    val soThich = varchar("Sở thích cá nhân", 1000)
    val soDienThoai = varchar("Số điện thoại", 20)
}