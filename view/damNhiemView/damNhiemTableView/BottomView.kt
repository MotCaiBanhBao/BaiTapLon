package luongvany.k12tt.view.damNhiemView.damNhiemTableView

import luongvany.k12tt.controller.DamNhiemController
import luongvany.k12tt.view.damNhiemView.addDamNhiemView.AddDamNhiem
import luongvany.k12tt.view.damNhiemView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val addDamNhiem: AddDamNhiem by inject()
    private val controller: DamNhiemController by inject()
    private val centerView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                addDamNhiem.openModal()
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
