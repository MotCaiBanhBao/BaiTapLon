package luongvany.k12tt.view.hDGD_DKView.hDGD_DKTableView

import luongvany.k12tt.controller.HDGD_DKController
import luongvany.k12tt.view.hDGD_DKView.addHDQD_DKView.AddHDGD_DK
import luongvany.k12tt.view.hDGD_DKView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val addHDGDDk: AddHDGD_DK by inject()
    private val controller: HDGD_DKController by inject()
    private val staffView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = staffView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addHDGDDk.openModal()
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
