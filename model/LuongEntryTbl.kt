package luongvany.k12tt.model

import org.jetbrains.exposed.sql.Table

object LuongEntryTbl : Table(){
    val maLuong = integer("Mã lương").primaryKey()
    val bacLuong = varchar("Bậc lương", 50)
    val luongCoBan = integer("Lương cơ bản")
    val heSoLuong = varchar("Hệ số lương", 50)
}