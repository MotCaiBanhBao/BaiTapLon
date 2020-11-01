package luongvany.k12tt.controller

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.model.datamodel.*
import org.jetbrains.exposed.sql.Table
import tornadofx.Controller
import java.util.*

class MainController: Controller(){
    val listOfObject = listOf<Table>(StaffEntryTbl, DepartmentEntryTbl, ChucVuEntryTbl,
            DamNhiemEntryTbl, DieuKhoanEntryTbl, DieuKhoanLaoDongEntryTbl,
            DoiTacEntryTbl, GioiThieuEntryTbl, HangHoaEntryTbl,
            HDDD_DKEntryTbl, HoaDonEntryTbl, HoiDongQuanTriEntryTbl,
            HopDongEntryTbl, KhachHangEntryTbl, LuongEntryTbl, NhapHangEntryTbl,
            PhuCapEntryTbl, ThanhVienHDQTEntryTbl)

    fun convertToId(name: SimpleStringProperty) = name.value.substring(4, name.value.indexOf(", Tên: ")).trim().toInt()
}