package luongvany.k12tt.view.departmentView.adddepartment

import luongvany.k12tt.controller.DepartmentController
import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.model.datamodel.DepartmentEntryModel
import luongvany.k12tt.util.toDepartmentEntry
import luongvany.k12tt.view.departmentView.FormView
import tornadofx.*

class AddDepartment: View(){
    private val model = DepartmentEntryModel()
    private val formView = FormView(model)
    private val controller: DepartmentController by inject()
    private val mainController: MainController by inject()
    private val hdqtController: HDQTController by inject()

    override val root = borderpane(){
        center =  formView.root
        bottom = button {
            text = "Save"
            action {
                model.directorateId.value = mainController.convertToId(formView.hdqt, ", Nhiệm kỳ: ")
                model.managerId.value = mainController.convertToId(formView.name, ", Tên: ")
                controller.add(model.toDepartmentEntry())
                model.rollback()
            }
        }
    }
}