package luongvany.k12tt.view.phuCapView.phuCapTableView

import luongvany.k12tt.controller.PhuCapController
import luongvany.k12tt.model.datamodel.PhuCapEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: PhuCapController by inject()
    var mTableView: TableViewEditModel<PhuCapEntryModel> by singleAssign()

    override val root = tableview(itemController.items){
        mTableView = editModel
        column("Id", PhuCapEntryModel::id)
        column("Ma Thanh Vien", PhuCapEntryModel::maThanhVien)
        column("Ngay Duoc Huong", PhuCapEntryModel::ngayDuocHuong)
        column("Thong Tin Phu Cap", PhuCapEntryModel::thongTinPhuCap)
    }
}
