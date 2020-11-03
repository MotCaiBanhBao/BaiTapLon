package luongvany.k12tt.view.hoaDonView.editTableView

import luongvany.k12tt.controller.HoaDonController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toHoaDonEntry
import luongvany.k12tt.view.hoaDonView.Form
import luongvany.k12tt.view.hoaDonView.hoaDonTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: HoaDonController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                controller.edit(content = selected.toHoaDonEntry(), indexItem = index)
            }
        }
    }
}
