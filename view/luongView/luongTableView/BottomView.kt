package luongvany.k12tt.view.luongView.luongTableView

import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.view.luongView.addLuongView.AddLuong
import luongvany.k12tt.view.luongView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val addLuong: AddLuong by inject()
    private val controller: LuongController by inject()
    private val centerView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addLuong.openModal()
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
