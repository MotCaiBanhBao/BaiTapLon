package luongvany.k12tt.model.datamodel

import org.jetbrains.exposed.sql.Table

object HDLD_DKEntryTbl: Table(){
    val maHopDong = integer("Mã hợp đồng").primaryKey().references(HopDongLaoDongEntryTbl.maHopDong)
    val maDieuKhoan = integer("Mã điều khoản").primaryKey().references(DieuKhoanLaoDongEntryTbl.maDieuKhoan)

}