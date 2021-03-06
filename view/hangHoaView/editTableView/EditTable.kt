package luongvany.k12tt.view.hangHoaView.editTableView

import luongvany.k12tt.controller.HangHoaController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toHangHoaEntry
import luongvany.k12tt.view.hangHoaView.Form
import luongvany.k12tt.view.hangHoaView.hangHoaTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: HangHoaController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)

    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action{
                controller.edit(content = selected.toHangHoaEntry(), indexItem = index)
            }
        }
    }
}
