package luongvany.k12tt.view.departmentview.editview

import luongvany.k12tt.controller.DepartmentController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toDepartmentEntry
import luongvany.k12tt.view.departmentview.departmenttableview.CenterView
import luongvany.k12tt.view.departmentview.FormView
import tornadofx.*

class EditView: View("My View") {
    private val department: CenterView by inject()
    private val controller: DepartmentController by inject()
    private val selected =  department.selectedDepartment.tableView.selectedItem!!.coppy()
    private val index =  department.selectedDepartment.tableView.selectedItem!!
    private val formView = FormView(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.managerId.value =  mainController.convertToId(formView.name)
                controller.edit(content = selected.toDepartmentEntry(), indexItem = index!!)
            }
        }
    }
}