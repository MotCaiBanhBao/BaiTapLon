package luongvany.k12tt.view.doiTacView.doiTacTableView

import luongvany.k12tt.controller.DoiTacController
import luongvany.k12tt.model.datamodel.DoiTacEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: DoiTacController by inject()
    var mTableView: TableViewEditModel<DoiTacEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Id", DoiTacEntryModel::id)
        column("Name", DoiTacEntryModel::name)
        column("Dia Chi", DoiTacEntryModel::diaChi)

    }
}
