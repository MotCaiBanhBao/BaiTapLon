package luongvany.k12tt.view.hoaDonView.hoaDonTableView

import luongvany.k12tt.controller.HoaDonController
import luongvany.k12tt.view.hoaDonView.addHoaDonView.AddHoaDon
import luongvany.k12tt.view.hoaDonView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val addHoaDon: AddHoaDon by inject()
    private val controller: HoaDonController by inject()
    private val centerView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addHoaDon.openModal()
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
