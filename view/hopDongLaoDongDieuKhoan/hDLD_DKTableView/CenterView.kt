package luongvany.k12tt.view.hopDongLaoDongDieuKhoan.hDLD_DKTableView

import luongvany.k12tt.controller.HDLD_DKController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.HDLD_DKEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: HDLD_DKController by inject()
    var mTableView: TableViewEditModel<HDLD_DKEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Mã HDLD", HDLD_DKEntryModel::maHDLD)
        column("Mã Điều Khoản", HDLD_DKEntryModel::maDieuKhoan)

    }
}
