package luongvany.k12tt.model

import org.jetbrains.exposed.sql.Table

object HopDongEntryTbl : Table(){
    val maHopDong = integer("Mã hợp đồng").primaryKey()
    val thoiGianThucHien = date("Hạn thời gian thực hiện")
}