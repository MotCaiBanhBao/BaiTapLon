package luongvany.k12tt.view.chucVuView.chuVuTableView

import luongvany.k12tt.controller.ChucVuController
import luongvany.k12tt.model.datamodel.ChucVuEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: ChucVuController by inject()
    var mTableView: TableViewEditModel<ChucVuEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Ma Chuc Vu", ChucVuEntryModel::maChucVu)
        column("Ten Chuc Vu", ChucVuEntryModel::tenChucVu)
    }
}
