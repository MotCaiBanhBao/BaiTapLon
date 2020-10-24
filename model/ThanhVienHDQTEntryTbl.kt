package luongvany.k12tt.model

import org.jetbrains.exposed.sql.Table

object ThanhVienHDQTEntryTbl : Table(){
    val maThanhVien = integer("Mã thành viên").primaryKey()
    val tenThanhVien = varchar("Tên thành viên", 100)
    val soDienThoai = varchar("Số điện thoại", 50)
    val soCMND = varchar("Số CMND", 50)
    val hoiDongQuanTri = integer("Mã hội đồng quản trị").references(HoiDongQuanTriEntryTbl.maHoiDongQuanTri)

}