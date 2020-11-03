package luongvany.k12tt.view.luongView.luongTableView

import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.model.datamodel.LuongEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: LuongController by inject()
    var mTableView: TableViewEditModel<LuongEntryModel> by singleAssign()

    override val root = tableview(itemController.items) {

        mTableView = editModel
        column("Id", LuongEntryModel::id)
        column("Bac Luong", LuongEntryModel::bacLuong)
        column("Luong Co Ban", LuongEntryModel::luongCoBan)
        column("He So Luong", LuongEntryModel::heSoLuong)

    }
}
