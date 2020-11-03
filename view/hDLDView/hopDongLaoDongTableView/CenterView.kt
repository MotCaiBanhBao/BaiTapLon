package luongvany.k12tt.view.hDLDView.hopDongLaoDongTableView

import luongvany.k12tt.controller.HopDongLaoDongController
import luongvany.k12tt.model.datamodel.HopDongLaoDongEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: HopDongLaoDongController by inject()
    var mTableView: TableViewEditModel<HopDongLaoDongEntryModel> by singleAssign()

    override val root = tableview(items = itemController.items){

        mTableView = editModel
        column("Id", HopDongLaoDongEntryModel::id)
        column("Ngày Thành Lập", HopDongLaoDongEntryModel::ngayThanhLap)

    }
}
