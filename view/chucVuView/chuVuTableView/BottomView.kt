package luongvany.k12tt.view.chucVuView.chuVuTableView

import luongvany.k12tt.controller.ChucVuController
import luongvany.k12tt.view.chucVuView.addChucVuView.AddChucVu
import luongvany.k12tt.view.phuCapView.editTableView.*

import tornadofx.*

class BottomView : View("My View") {
    private val bangThemChucVu: AddChucVu by inject()
    private val controller: ChucVuController by inject()
    private val centerView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                bangThemChucVu.openModal()
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
