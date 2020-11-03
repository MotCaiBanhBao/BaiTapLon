package luongvany.k12tt.view.dieuKhoanView.dieuKhoanTableView

import luongvany.k12tt.controller.DieuKhoanController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.DieuKhoanEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: DieuKhoanController by inject()
    var mTableView: TableViewEditModel<DieuKhoanEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Id", DieuKhoanEntryModel::id)
        column("Noi Dung", DieuKhoanEntryModel::noiDung)


    }
}
