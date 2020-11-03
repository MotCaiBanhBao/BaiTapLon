package luongvany.k12tt.view.departmentView.editview

import luongvany.k12tt.controller.DepartmentController
import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toDepartmentEntry
import luongvany.k12tt.view.departmentView.departmenttableview.CenterView
import luongvany.k12tt.view.departmentView.FormView
import tornadofx.*

class EditView: View("My View") {
    private val department: CenterView by inject()
    private val controller: DepartmentController by inject()
    private val selected =  department.selectedDepartment.tableView.selectedItem!!.coppy()
    private val index =  department.selectedDepartment.tableView.selectedItem!!
    private val formView = FormView(selected)
    private val mainController: MainController by inject()
    private val hdqt: HDQTController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.directorateId.value = mainController.convertToId(formView.hdqt,", Nhiệm kỳ: ")
                selected.managerId.value =  mainController.convertToId(formView.name, ", Tên: ")
                controller.edit(content = selected.toDepartmentEntry(), indexItem = index)
            }
        }
    }
}