package luongvany.k12tt.view.dieuKhoanView.dieuKhoanTableView

import luongvany.k12tt.controller.DieuKhoanController
import luongvany.k12tt.view.dieuKhoanView.addDieuKhoanView.AddDieuKhoan
import luongvany.k12tt.view.dieuKhoanView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val addDieuKhoan: AddDieuKhoan by inject()
    private val controller: DieuKhoanController by inject()
    private val centerView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addDieuKhoan.openModal()
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
