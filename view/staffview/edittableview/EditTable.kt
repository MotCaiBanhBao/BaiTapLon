package luongvany.k12tt.view.staffview.edittableview

import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toStaffEntry
import luongvany.k12tt.view.staffview.Form
import luongvany.k12tt.view.staffview.stafftableview.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val staffView: CenterView by inject()
    private val controller: StaffController by inject()
    private val selected =  staffView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  staffView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                println(selected.id)
                controller.edit(content = selected.toStaffEntry(), indexItem = index!!)
            }
        }
    }
}
