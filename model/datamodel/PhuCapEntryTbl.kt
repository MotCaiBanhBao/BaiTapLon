package luongvany.k12tt.model.datamodel

import org.jetbrains.exposed.sql.Table

object PhuCapEntryTbl : Table(){
    val maPhuCap = integer("Mã phụ cấp").primaryKey()
    val maThanhVien = integer("Mã thành viên").references(ThanhVienHDQTEntryTbl.maThanhVien)
    val ngayDuocHuong = date("Ngày được thụ hưởng")
    val thongTinPhuCap = varchar("Thông tin phụ cấp", 1000)
}