package luongvany.k12tt.view.hDGD_DKView.hDGD_DKTableView

import luongvany.k12tt.controller.HDGD_DKController
import luongvany.k12tt.model.datamodel.HDGD_DKEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: HDGD_DKController by inject()
    var mTableView: TableViewEditModel<HDGD_DKEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Ma Hop Dong", HDGD_DKEntryModel::maHopDong)
        column("Ma Dieu Khoan", HDGD_DKEntryModel::maDieuKhoan)

    }
}
