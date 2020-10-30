package luongvany.k12tt.view.staffview.stafftableview

import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.view.staffview.addstaffview.AddStaff
import luongvany.k12tt.view.staffview.edittableview.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val bangThemNhanVien: AddStaff by inject()
    private val controller: StaffController by inject()
    private val staffView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = staffView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                bangThemNhanVien.openModal()
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
