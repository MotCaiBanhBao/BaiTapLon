package luongvany.k12tt.view.damNhiemView.editTableView

import luongvany.k12tt.controller.DamNhiemController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toDamNhiemEntry
import luongvany.k12tt.view.damNhiemView.Form
import luongvany.k12tt.view.damNhiemView.damNhiemTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: DamNhiemController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.maChucVu.value = mainController.convertToId(formView.chucVu, ", Tên: ")
                selected.maHopDong.value = mainController.convertToId(formView.hdld, ", Ngày thành lập: ")
                selected.maNhanVien.value = mainController.convertToId(formView.nhanVien, ", Tên: ")
                controller.edit(content = selected.toDamNhiemEntry(), indexItem = index)
            }
        }
    }
}
