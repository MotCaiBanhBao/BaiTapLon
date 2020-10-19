package luongvany.k12tt.view.staffview.stafftableview

import luongvany.k12tt.controller.StaffViewController
import luongvany.k12tt.view.staffview.addstaffview.AddStaff
import tornadofx.*

class BottomView : View("My View") {
    private val staffViewController: StaffViewController by inject()
    private val bangThemNhanVien: AddStaff by inject()
    private val leftView: CenterView by inject()
    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                bangThemNhanVien.openModal()
            }
        }

        button("Delete"){
            shortcut("delete")
            action {
                staffViewController.deleteRow(staffViewController.findIndex())
            }
        }

    }
}
