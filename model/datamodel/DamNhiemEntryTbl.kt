package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

object DamNhiemEntryTbl: Table(){
    val maChucVu = integer("Mã chức vụ").references(ChucVuEntryTbl.maChucVu)
    val maHopDong = integer("Mã hợp đồng").references(HopDongLaoDongEntryTbl.maHopDong)
    val maNhanVien = integer("maNhanVien").references(StaffEntryTbl.id)
}
fun ResultRow.toDamNhiemEntry() = DamNhiemEntry(this[DamNhiemEntryTbl.maChucVu], this[DamNhiemEntryTbl.maHopDong], this[DamNhiemEntryTbl.maNhanVien])

class DamNhiemEntry(maChucVu: Int, maHopDong: Int, maNhanVien: Int){
    val maChucVuProperty = SimpleIntegerProperty(maChucVu)
    var maChucVu by maChucVuProperty

    val maHopDongProperty = SimpleIntegerProperty(maHopDong)
    var maHopDong by maHopDongProperty

    val maNhanVienProperty = SimpleIntegerProperty(maNhanVien)
    var maNhanVien by maNhanVienProperty
}

class DamNhiemEntryModel : ItemViewModel<DamNhiemEntry>() {
    val maChucVu = bind(DamNhiemEntry::maChucVuProperty)
    val maHopDong = bind(DamNhiemEntry::maHopDongProperty)
    val maNhanVien = bind(DamNhiemEntry::maNhanVienProperty)
}



