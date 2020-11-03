package luongvany.k12tt.view.hopDongView.editTableView

import luongvany.k12tt.controller.HopDongController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toHopDongEntry
import luongvany.k12tt.view.hopDongView.Form
import luongvany.k12tt.view.hopDongView.hDTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: HopDongController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                controller.edit(content = selected.toHopDongEntry(), indexItem = index)
            }
        }
    }
}
