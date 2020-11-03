package luongvany.k12tt.view.dieuKhoanLaoDong.editTableView

import luongvany.k12tt.controller.DieuKhoanLaoDongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toDieuKhoanLaoDongEntry
import luongvany.k12tt.view.dieuKhoanLaoDong.Form
import luongvany.k12tt.view.dieuKhoanLaoDong.dKLDTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: DieuKhoanLaoDongController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                controller.edit(content = selected.toDieuKhoanLaoDongEntry(), indexItem = index)
            }
        }
    }
}
