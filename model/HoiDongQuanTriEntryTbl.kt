package luongvany.k12tt.model

import org.jetbrains.exposed.sql.Table

object HoiDongQuanTriEntryTbl : Table(){
    val maHoiDongQuanTri = integer("Hội đồng quản trị").primaryKey()
    val nhiemKy = varchar("Nhiệm kỳ", 100)
}