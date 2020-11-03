package luongvany.k12tt.view.chucVuView.chuVuTableView

import tornadofx.*

class StaffView: View("Bảng chức vụ"){

    private val bottomView: BottomView by inject()
    private val centerView: CenterView by inject()

    override fun onDock() {
        currentWindow?.width = 1200.0
        currentWindow?.height = 600.0
        currentWindow?.centerOnScreen()
    }

    override val root = borderpane(){
        center = centerView.root
        bottom = bottomView.root
    }

}
