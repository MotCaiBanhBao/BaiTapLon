package luongvany.k12tt.view.khachHangView.editTableView

import luongvany.k12tt.controller.KhachHangController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toKhachHangEntry
import luongvany.k12tt.view.khachHangView.Form
import luongvany.k12tt.view.khachHangView.staffTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: KhachHangController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)

    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                controller.edit(content = selected.toKhachHangEntry(), indexItem = index)
            }
        }
    }
}
