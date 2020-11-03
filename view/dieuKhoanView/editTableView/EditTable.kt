package luongvany.k12tt.view.dieuKhoanView.editTableView

import luongvany.k12tt.controller.DieuKhoanController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toDieuKhoanEntry
import luongvany.k12tt.view.dieuKhoanView.Form
import luongvany.k12tt.view.dieuKhoanView.dieuKhoanTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: DieuKhoanController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {

                controller.edit(content = selected.toDieuKhoanEntry(), indexItem = index)
            }
        }
    }
}
