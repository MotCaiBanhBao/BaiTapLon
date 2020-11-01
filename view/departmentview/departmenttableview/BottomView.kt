package luongvany.k12tt.view.departmentview.departmenttableview

import luongvany.k12tt.controller.DepartmentController
import luongvany.k12tt.view.departmentview.editview.EditView
import luongvany.k12tt.view.departmentview.adddepartment.AddDepartment
import tornadofx.*

class BottomView: View(){
    private val department: CenterView by inject()
    private val addView: AddDepartment by inject()
    private val editView: EditView by inject()
    private val controller: DepartmentController by inject()
    private var selectedItem = department.selectedDepartment.tableView.selectedItem

    override val root = hbox {
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addView.openModal(owner = null)
            }
        }
        button{
            action {
            selectedItem = department.selectedDepartment.tableView.selectedItem
            selectedItem?.let{
                controller.delete(it)
            }
        }
            text = "Delete"
        }
        button{
            text = "Edit"
            action{
                selectedItem = department.selectedDepartment.tableView.selectedItem
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