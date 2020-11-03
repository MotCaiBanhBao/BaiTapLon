package luongvany.k12tt.view.hDLDView.hopDongLaoDongTableView

import luongvany.k12tt.controller.HopDongLaoDongController
import luongvany.k12tt.view.hDLDView.addHopDongLaoDongView.AddHDLD
import luongvany.k12tt.view.hDLDView.editTableView.EditTable
import tornadofx.*

class BottomView : View("My View") {
    private val bangThemHDLD: AddHDLD by inject()
    private val controller: HopDongLaoDongController by inject()
    private val centerView: CenterView by inject()
    private val editView: EditTable by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                bangThemHDLD.openModal()
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
