package luongvany.k12tt.view.departmentview.adddepartment

import luongvany.k12tt.controller.DepartmentController
import luongvany.k12tt.model.datamodel.DepartmentEntryModel
import luongvany.k12tt.util.toDepartmentEntry
import luongvany.k12tt.view.departmentview.FormView
import tornadofx.*

class AddDepartment: View(){
    private val model = DepartmentEntryModel()
    private val formView = FormView(model)
    private val controller: DepartmentController by inject()

    override val root = borderpane(){
        center = formView.root
        bottom = button {
            text = "Save"
            action {
                model.managerId.value = controller.convertToId(formView.name)
                controller.add(model.toDepartmentEntry())
            }
        }
    }
}