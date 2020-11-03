package luongvany.k12tt.view.hangHoaView.hangHoaTableView

import luongvany.k12tt.controller.HangHoaController
import luongvany.k12tt.view.hangHoaView.addHangHoaView.AddHangHoa
import luongvany.k12tt.view.staffView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val addHangHoa: AddHangHoa by inject()
    private val controller: HangHoaController by inject()
    private val centerView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addHangHoa.openModal()
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
