package luongvany.k12tt.view.gioiThieuView.editTableView

import luongvany.k12tt.controller.GioiThieuController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toGioiThieuEntry
import luongvany.k12tt.view.gioiThieuView.Form
import luongvany.k12tt.view.gioiThieuView.gioiThieuTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: GioiThieuController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.maHangHoa.value = mainController.convertToId(formView.hangHoa, ", Tên: ")
                selected.maHopDong.value = mainController.convertToId(formView.hopDong, ", Thòi gian thực hiện: ")
                selected.maKhachHang.value = mainController.convertToId(formView.khachHang, ", Tên: ")
                selected.maNhanVien.value = mainController.convertToId(formView.staff, ", Tên: ")
                controller.edit(content = selected.toGioiThieuEntry(), indexItem = index)
            }
        }
    }
}
