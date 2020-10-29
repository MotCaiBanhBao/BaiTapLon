package luongvany.k12tt.model.datamodel

import org.jetbrains.exposed.sql.Table

object ChucVuEntryTbl: Table(){
    val maChucVu = integer("Mã chức vụ").primaryKey()
    val tenChucVu = varchar("Tên chức vụ", 100)
}

