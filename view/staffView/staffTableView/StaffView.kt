package luongvany.k12tt.view.staffView.staffTableView

import tornadofx.*

class StaffView: View("Bảng nhân viên"){

    private val bottomView: BottomView by inject()
    private val centerView: CenterView by inject()
    private val rightView: RightView by inject()

    override fun onDock() {
        currentWindow?.width = 1200.0
        currentWindow?.height = 600.0
        currentWindow?.centerOnScreen()
    }

    override val root = borderpane(){

        center = centerView.root
        right = rightView.root
        bottom = bottomView.root
    }

}
