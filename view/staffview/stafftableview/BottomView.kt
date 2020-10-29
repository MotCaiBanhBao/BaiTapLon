package luongvany.k12tt.view.staffview.stafftableview

import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.view.staffview.addstaffview.AddStaff
import tornadofx.*

class BottomView : View("My View") {
    private val bangThemNhanVien: AddStaff by inject()
    private val controller: StaffController by inject()
    private val staffView: CenterView by inject()

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

                val selectedItem = staffView.mTableView.tableView.selectedItem

                controller.delete(selectedItem!!)
            }
        }

    }
}
