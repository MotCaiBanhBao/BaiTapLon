package luongvany.k12tt.model

import org.jetbrains.exposed.sql.Table

object HDDD_DKEntryTbl : Table(){
    val maHopDong = integer("Mã hợp đồng").references(HopDongEntryTbl.maHopDong).primaryKey()
    val maDieuKhoan = integer("Mã điều khoản").references(DieuKhoanEntryTbl.maDieuKhoan).primaryKey()

}