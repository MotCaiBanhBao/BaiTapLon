package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

object HDGD_DKEntryTbl : Table(){
    val maHopDong = integer("Mã hợp đồng").references(HopDongEntryTbl.maHopDong).primaryKey()
    val maDieuKhoan = integer("Mã điều khoản").references(DieuKhoanEntryTbl.maDieuKhoan).primaryKey()
}
fun ResultRow.toHDGD_DKEntry() = HDGD_DKEntry(this[HDGD_DKEntryTbl.maHopDong], this[HDGD_DKEntryTbl.maDieuKhoan])

class HDGD_DKEntry(maHopDong: Int, maDieuKhoan: Int, ){
    val maHopDongProperty = SimpleIntegerProperty(maHopDong)
    var maHopDong by maHopDongProperty

    val maDieuKhoanProperty = SimpleIntegerProperty(maDieuKhoan)
    var maDieuKhoan by maDieuKhoanProperty
}

class HDGD_DKEntryModel : ItemViewModel<HDGD_DKEntry>() {
    val maHopDong = bind(HDGD_DKEntry::maHopDongProperty)
    val maDieuKhoan = bind(HDGD_DKEntry::maDieuKhoanProperty)
}
