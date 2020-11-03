package luongvany.k12tt.view.khachHangView.staffTableView

import luongvany.k12tt.controller.KhachHangController
import luongvany.k12tt.view.khachHangView.addKhachHangView.AddKhachHang
import luongvany.k12tt.view.staffView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val addKhachHang: AddKhachHang by inject()
    private val controller: KhachHangController by inject()
    private val centerView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addKhachHang.openModal()
            }
        }

        button("Delete"){
            shortcut("delete")
            action {
                selectedItem = centerView.mTableView.tableView.selectedItem
                selectedItem?.let{
                    controller.delete(it)
                }
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
