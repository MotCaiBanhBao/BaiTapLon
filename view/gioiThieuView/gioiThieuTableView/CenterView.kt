package luongvany.k12tt.view.gioiThieuView.gioiThieuTableView

import luongvany.k12tt.controller.GioiThieuController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.GioiThieuEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: GioiThieuController by inject()
    var mTableView: TableViewEditModel<GioiThieuEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Mã giới thiệu", GioiThieuEntryModel::maGioiThieu)
        column("Mã nhân viên", GioiThieuEntryModel::maNhanVien)
        column("Mã hợp đồng", GioiThieuEntryModel::maHopDong)
        column("Mã hàng hóa", GioiThieuEntryModel::maHangHoa)
        column("Mã khách hàng", GioiThieuEntryModel::maKhachHang)

    }
}
