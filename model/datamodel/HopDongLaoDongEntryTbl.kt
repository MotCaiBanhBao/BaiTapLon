package luongvany.k12tt.model.datamodel

import org.jetbrains.exposed.sql.Table

object HopDongLaoDongEntryTbl : Table(){
    val maHopDong = integer("Mã hợp đồng").primaryKey()
    val ngayThanhLap = date("Ngày thành lập")
}