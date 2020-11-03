package luongvany.k12tt.view.nhapHangView.nhapHangTableView

import luongvany.k12tt.controller.NhapHangController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.NhapHangEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: NhapHangController by inject()
    var mTableView: TableViewEditModel<NhapHangEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Id", NhapHangEntryModel::id)
        column("Mã Nhân Viên", NhapHangEntryModel::maNhanVien)
        column("Mã Hóa Đơn", NhapHangEntryModel::maHoaDon)
        column("Mã Hàng Hóa", NhapHangEntryModel::maHangHoa)
        column("Mã Đối Tác", NhapHangEntryModel::maDoiTac)
    }
}
