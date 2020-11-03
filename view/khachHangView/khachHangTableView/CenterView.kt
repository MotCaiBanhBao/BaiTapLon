package luongvany.k12tt.view.khachHangView.khachHangTableView

import luongvany.k12tt.controller.KhachHangController
import luongvany.k12tt.model.datamodel.KhachHangEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: KhachHangController by inject()
    var mTableView: TableViewEditModel<KhachHangEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Id", KhachHangEntryModel::id)
        column("Địa Chỉ", KhachHangEntryModel::diaChi)
        column("Tên Khách Hàng", KhachHangEntryModel::tenKhachHang)
        column("Sở Thích", KhachHangEntryModel::soThich)
        column("Số Điện Thoại", KhachHangEntryModel::soDienThoai)


    }
}
