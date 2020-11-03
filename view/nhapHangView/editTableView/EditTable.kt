package luongvany.k12tt.view.nhapHangView.editTableView

import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.NhapHangController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toNhapHangEntry
import luongvany.k12tt.view.nhapHangView.Form
import luongvany.k12tt.view.nhapHangView.nhapHangTableView.CenterView
import tornadofx.*

class EditTable: View("My View") {
    private val centerView: CenterView by inject()
    private val controller: NhapHangController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)
    private val mainController: MainController by inject()
    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.maNhanVien.value = mainController.convertToId(formView.staff, ", Tên: ")
                selected.maDoiTac.value = mainController.convertToId(formView.doiTac, ", Tên: ")
                selected.maHangHoa.value = mainController.convertToId(formView.hangHoa, ", Tên: ")
                selected.maHoaDon.value = mainController.convertToId(formView.hoaDon, ", Ngày Lập: ")
                controller.edit(content = selected.toNhapHangEntry(), indexItem = index)
            }
        }
    }
}
