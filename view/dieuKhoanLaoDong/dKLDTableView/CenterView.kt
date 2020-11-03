package luongvany.k12tt.view.dieuKhoanLaoDong.dKLDTableView

import luongvany.k12tt.controller.DieuKhoanLaoDongController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.DieuKhoanLaoDongEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: DieuKhoanLaoDongController by inject()
    var mTableView: TableViewEditModel<DieuKhoanLaoDongEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Id", DieuKhoanLaoDongEntryModel::id)
        column("Nội dung", DieuKhoanLaoDongEntryModel::noiDung)

    }
}
