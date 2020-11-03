package luongvany.k12tt.view.hoiDongQuanTriView.hoiDongQuanTriTableView

import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.view.hoiDongQuanTriView.addHoiDongQT.AddHDQT
import luongvany.k12tt.view.hoiDongQuanTriView.editView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val themHDQT: AddHDQT by inject()
    private val centerView: CenterView by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem
    private val hdqtController: HDQTController by inject()
    private val editView: EditTable by inject()

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                themHDQT.openModal()
            }
        }

        button("Delete"){
            shortcut("delete")
            action {
                selectedItem = centerView.mTableView.tableView.selectedItem
                selectedItem?.let{
                    hdqtController.delete(it)
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
