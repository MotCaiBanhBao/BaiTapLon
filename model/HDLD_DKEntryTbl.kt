package luongvany.k12tt.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.*
import tornadofx.*

object HDLD_DKEntryTbl: Table(){
    val maHopDong = integer("Mã hợp đồng").primaryKey().references(HopDongLaoDongEntryTbl.maHopDong)
    val maDieuKhoan = integer("Mã điều khoản").primaryKey().references(DieuKhoanLaoDongEntryTbl.maDieuKhoan)

}