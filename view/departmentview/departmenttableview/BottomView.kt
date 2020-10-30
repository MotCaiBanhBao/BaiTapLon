package luongvany.k12tt.view.departmentview.departmenttableview

import javafx.scene.Parent
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.DepartmentEntryModel
import luongvany.k12tt.view.departmentview.FormView
import tornadofx.*

class BottomView: View(){
    private val model = DepartmentEntryModel()
    private val formView = FormView(model)

    override val root = hbox {
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                formView.openModal(owner = null)
            }
        }
    }
}