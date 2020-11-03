package luongvany.k12tt.view.hDGD_DKView.editTableView

import luongvany.k12tt.controller.HDGD_DKController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toHDGD_DKEntry
import luongvany.k12tt.view.hDGD_DKView.Form
import luongvany.k12tt.view.hDGD_DKView.hDGD_DKTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: HDGD_DKController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.maDieuKhoan.value = mainController.convertToId(formView.dieuKhoan, ", Điều khoản: ")
                selected.maHopDong.value = mainController.convertToId(formView.hopDong, ", Thòi gian thực hiện: ")
                controller.edit(content = selected.toHDGD_DKEntry(), indexItem = index)
            }
        }
    }
}
