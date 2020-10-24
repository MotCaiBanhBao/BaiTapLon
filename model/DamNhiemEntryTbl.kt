package luongvany.k12tt.model

import org.jetbrains.exposed.sql.Table

object DamNhiemEntryTbl: Table(){
    val maChucVu = integer("Mã chức vụ").references(ChucVuEntryTbl.maChucVu)
    val maHopDong = integer("Mã hợp đồng").references(HopDongLaoDongEntryTbl.maHopDong)
    val maNhanVien = integer("maNhanVien").references(StaffEntryTbl.id)
}