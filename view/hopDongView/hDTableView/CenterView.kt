package luongvany.k12tt.view.hopDongView.hDTableView

import luongvany.k12tt.controller.HopDongController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.HopDongEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: HopDongController by inject()
    var mTableView: TableViewEditModel<HopDongEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Id", HopDongEntryModel::id)
        column("Thoi Gian Thuc Hien", HopDongEntryModel::thoiGianThucHien)

    }
}
