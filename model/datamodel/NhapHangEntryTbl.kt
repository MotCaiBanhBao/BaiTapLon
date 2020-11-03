package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*

object NhapHangEntryTbl: Table() {
    val maNhapHang = integer("Mã nhập hàng").autoIncrement().primaryKey()
    val maNhanVien = integer("Nhân viên nhập hàng").references(StaffEntryTbl.id).nullable()
    val maHoaDon = integer("Mã hóa đơn").references(HoaDonEntryTbl.maHoaDon).nullable()
    val maHangHoa = integer("Mã hàng hóa").references(HangHoaEntryTbl.maHangHoa).nullable()
    val maDoiTac = integer("Mã đối tác").references(DoiTacEntryTbl.maDoiTac).nullable()
}

fun ResultRow.toNhapHangEntry() = this[NhapHangEntryTbl.maNhanVien]?.let {
    this[NhapHangEntryTbl.maHoaDon]?.let { it1 ->
        this[NhapHangEntryTbl.maHangHoa]?.let { it2 ->
            this[NhapHangEntryTbl.maDoiTac]?.let { it3 ->
                NhapHangEntry(this[NhapHangEntryTbl.maNhapHang],
                    it,
                        it1,
                        it2,
                        it3
                )
            }
        }
    }
}

class NhapHangEntry(id: Int, maNhanVien: Int, maHoaDon: Int, maHangHoa: Int, maDoiTac: Int){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val maNhanVienProperty = SimpleIntegerProperty(maNhanVien)
    var maNhanVien by maNhanVienProperty

    val maHoaDonProperty = SimpleIntegerProperty(maHoaDon)
    var maHoaDon by maHoaDonProperty

    val maHangHoaProperty = SimpleIntegerProperty(maHangHoa)
    var maHangHoa by maHangHoaProperty

    val maDoiTacProperty = SimpleIntegerProperty(maDoiTac)
    var maDoiTac by maDoiTacProperty

}

class NhapHangEntryModel : ItemViewModel<NhapHangEntry>() {
    val id = bind(NhapHangEntry::idProperty)
    val maNhanVien = bind(NhapHangEntry::maNhanVienProperty)
    val maHoaDon = bind(NhapHangEntry::maHoaDonProperty)
    val maHangHoa = bind(NhapHangEntry::maHangHoaProperty)
    val maDoiTac = bind(NhapHangEntry::maDoiTacProperty)
}




