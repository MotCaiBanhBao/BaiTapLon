package luongvany.k12tt.view.thanhVienHDQT.editThanhVien


import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.ThanhVienHDQTController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toThanhVienHDQTEntry
import luongvany.k12tt.view.thanhVienHDQT.Form
import luongvany.k12tt.view.thanhVienHDQT.thanhVienHDQT.CenterView
import tornadofx.*

class EditView : View(){
    private val centerView: CenterView by inject()
    private val controller: ThanhVienHDQTController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.hoiDongQuantri.value = mainController.convertToId(formView.hdqt, ", Nhiệm kỳ")
                controller.edit(content = selected.toThanhVienHDQTEntry(), indexItem = index)
            }
        }
    }
}