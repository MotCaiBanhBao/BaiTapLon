package luongvany.k12tt.view.thanhVienHDQT.thanhVienHDQT

import luongvany.k12tt.controller.ThanhVienHDQTController
import luongvany.k12tt.view.thanhVienHDQT.addThanhVien.AddThanhVienView
import luongvany.k12tt.view.thanhVienHDQT.editThanhVien.EditView
import tornadofx.*

class BottomView : View(){
    private val bangThemThanhVien: AddThanhVienView by inject()
    private val controller: ThanhVienHDQTController by inject()
    private val centerView: CenterView by inject()
    private val editView: EditView by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem
    override val root = hbox {
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                bangThemThanhVien.openModal()
            }
        }

        button("Edit"){
            action {
                selectedItem = centerView.mTableView.tableView.selectedItem
                if(selectedItem == null){
                    warning("Lỗi", content = "Làm ơn hãy chọn đối tượng trước khi chỉnh sửa")
                }
                selectedItem?.let{
                    editView.openModal(owner = null)
                }
            }
        }
    }
}