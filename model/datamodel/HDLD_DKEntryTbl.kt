package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import luongvany.k12tt.util.convertToSex
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

object HDLD_DKEntryTbl: Table(){
    val maHopDong = integer("Mã hợp đồng").primaryKey().references(HopDongLaoDongEntryTbl.maHopDong)
    val maDieuKhoan = integer("Mã điều khoản").primaryKey().references(DieuKhoanLaoDongEntryTbl.maDieuKhoan)
}

fun ResultRow.toHDLD_DKEntry() = HDLD_DKEntry(
    this[HDLD_DKEntryTbl.maHopDong],
    this[HDLD_DKEntryTbl.maDieuKhoan]
)

class HDLD_DKEntry(maHopDong: Int, maDieuKhoan: Int){
    val maHDLDProperty = SimpleIntegerProperty(maHopDong)
    var maHDLD by maHDLDProperty

    val maDieuKhoanProperty = SimpleIntegerProperty(maDieuKhoan)
    var maDieuKhoan by maDieuKhoanProperty
}

class HDLD_DKEntryModel : ItemViewModel<HDLD_DKEntry>() {
    val maHDLD = bind(HDLD_DKEntry::maHDLDProperty)
    val maDieuKhoan = bind(HDLD_DKEntry::maDieuKhoanProperty)
}
