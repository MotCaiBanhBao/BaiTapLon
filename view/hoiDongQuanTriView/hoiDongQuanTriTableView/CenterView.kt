package luongvany.k12tt.view.hoiDongQuanTriView.hoiDongQuanTriTableView

import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.model.datamodel.HoiDongQuanTriEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val hdqtController: HDQTController by inject()
    var mTableView: TableViewEditModel<HoiDongQuanTriEntryModel> by singleAssign()

    override val root = tableview(hdqtController.items){
        mTableView = editModel
        column("Mã Hội Đồng Quản Trị", HoiDongQuanTriEntryModel::maHoiDongQuanTri)
        column("Nhiệm Kỳ", HoiDongQuanTriEntryModel::nhiemKy)

        onSelectionChange {
            mTableView.tableView.selectedItem?.let {  }
        }
    }
}
