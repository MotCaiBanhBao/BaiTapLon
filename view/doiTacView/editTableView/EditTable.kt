package luongvany.k12tt.view.doiTacView.editTableView

import luongvany.k12tt.controller.DoiTacController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toDoiTacEntry
import luongvany.k12tt.view.doiTacView.Form
import luongvany.k12tt.view.doiTacView.doiTacTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: DoiTacController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)

    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                controller.edit(content = selected.toDoiTacEntry(), indexItem = index)
            }
        }
    }
}
