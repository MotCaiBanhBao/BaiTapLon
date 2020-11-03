package luongvany.k12tt.view.hDLDView.editTableView

import luongvany.k12tt.controller.HopDongLaoDongController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toHopDongEntry
import luongvany.k12tt.view.hDLDView.Form
import luongvany.k12tt.view.hDLDView.hopDongLaoDongTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: HopDongLaoDongController by inject()
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
