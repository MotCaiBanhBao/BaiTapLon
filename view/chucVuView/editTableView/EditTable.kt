package luongvany.k12tt.view.chucVuView.editTableView

import luongvany.k12tt.controller.ChucVuController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toChucVuEntry
import luongvany.k12tt.view.chucVuView.Form
import luongvany.k12tt.view.chucVuView.chuVuTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: ChucVuController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                controller.edit(content = selected.toChucVuEntry(), indexItem = index)
            }
        }
    }
}
