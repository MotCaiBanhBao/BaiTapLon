package luongvany.k12tt.view.luongView.editTableView

import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toLuongEntry
import luongvany.k12tt.view.luongView.Form
import luongvany.k12tt.view.luongView.luongTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: LuongController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                controller.edit(content = selected.toLuongEntry(), indexItem = index)
            }
        }
    }
}
