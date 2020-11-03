package luongvany.k12tt.view.hangHoaView.hangHoaTableView

import luongvany.k12tt.controller.HangHoaController
import luongvany.k12tt.model.datamodel.HangHoaEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: HangHoaController by inject()
    var mTableView: TableViewEditModel<HangHoaEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Mã hàng hóa", HangHoaEntryModel::maHangHoa)
        column("Tên", HangHoaEntryModel::ten)
        column("Giới Tính", HangHoaEntryModel::gioiTinh)
        column("Năm Sinh", HangHoaEntryModel::namSinh)
    }
}
