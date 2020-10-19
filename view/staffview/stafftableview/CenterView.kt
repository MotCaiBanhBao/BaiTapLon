package luongvany.k12tt.view.staffview.stafftableview

import luongvany.k12tt.controller.StaffViewController
import luongvany.k12tt.model.Staff
import tornadofx.*

class CenterView : View("My View") {
    val rightView: RightView by inject()
    private val mainController: StaffViewController by inject()

    override val root = tableview(mainController.staffDatas){

        isEditable = true
        column("Id", Staff::idProperty).makeEditable()
        column("Name", Staff::nameProperty).makeEditable()
        column("Home Town", Staff::homeTownProperty).makeEditable()
        column("Sex", Staff::sexProperty)
        readonlyColumn("Age", Staff::age)
        column("Department", Staff::departmentIdProperty).makeEditable()
        column("Salary id", Staff::salaryIdProperty).makeEditable()
        onSelectionChange {
            mainController.changeImg(mainController.findIndex())
        }
    }
}
