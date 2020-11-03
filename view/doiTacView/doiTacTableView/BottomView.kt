package luongvany.k12tt.view.doiTacView.doiTacTableView

import luongvany.k12tt.controller.DoiTacController
import luongvany.k12tt.view.doiTacView.addDoiTacView.AddDoiTac
import luongvany.k12tt.view.doiTacView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val addDoiTac: AddDoiTac by inject()
    private val controller: DoiTacController by inject()
    private val staffView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = staffView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addDoiTac.openModal()
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
