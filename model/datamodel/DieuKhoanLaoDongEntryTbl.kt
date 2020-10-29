package luongvany.k12tt.model.datamodel

import org.jetbrains.exposed.sql.Table

object DieuKhoanLaoDongEntryTbl: Table(){
    val maDieuKhoan = integer("Mã điều khoản").primaryKey()
    val noiDung = varchar("Nội dung", 1000)
}

