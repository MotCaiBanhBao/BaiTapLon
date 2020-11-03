package luongvany.k12tt.view.phuCapView.phuCapTableView

import luongvany.k12tt.controller.PhuCapController
import luongvany.k12tt.view.phuCapView.addPhuCapView.AddPhuCap
import luongvany.k12tt.view.phuCapView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val addPhuCap: AddPhuCap by inject()
    private val controller: PhuCapController by inject()
    private val staffView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = staffView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addPhuCap.openModal()
            }
        }

        button("Delete"){
            shortcut("delete")
            action {
                selectedItem = staffView.mTableView.tableView.selectedItem
                selectedItem?.let{
                    controller.delete(it)
                }
            }
        }
        button("Edit"){
            action {
                selectedItem = staffView.mTableView.tableView.selectedItem
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
