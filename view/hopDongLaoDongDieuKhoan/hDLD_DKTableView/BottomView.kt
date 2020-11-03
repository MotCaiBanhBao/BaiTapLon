package luongvany.k12tt.view.hopDongLaoDongDieuKhoan.hDLD_DKTableView

import luongvany.k12tt.controller.HDLD_DKController
import luongvany.k12tt.view.hopDongLaoDongDieuKhoan.addHDLD_DKView.AddHDLD_DK
import tornadofx.*

class BottomView : View("My View") {
    private val bangThem: AddHDLD_DK by inject()
    private val controller: HDLD_DKController by inject()
    private val centerView: CenterView by inject()
    private var selectedItem = centerView.mTableView.tableView.selectedItem

    override val root = hbox{
        button {
            text = "Add"
            shortcut("ctrl+n")
            action {
                bangThem.openModal()
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
    }
}
