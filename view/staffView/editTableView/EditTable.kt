package luongvany.k12tt.view.staffView.editTableView

import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toStaffEntry
import luongvany.k12tt.view.staffView.Form
import luongvany.k12tt.view.staffView.staffTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val staffView: CenterView by inject()
    private val controller: StaffController by inject()
    private val selected =  staffView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  staffView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.departmentId.value = mainController.convertToId(formView.name, ", Tên: ")
                controller.edit(content = selected.toStaffEntry(), indexItem = index)
            }
        }
    }
}
