package luongvany.k12tt.view.phuCapView.editTableView

import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.PhuCapController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toPhuCapEntry
import luongvany.k12tt.view.phuCapView.Form
import luongvany.k12tt.view.phuCapView.phuCapTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: PhuCapController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.maThanhVien.value = mainController.convertToId(formView.name, ", Tên: ")
                controller.edit(content = selected.toPhuCapEntry(), indexItem = index)
            }
        }
    }
}
