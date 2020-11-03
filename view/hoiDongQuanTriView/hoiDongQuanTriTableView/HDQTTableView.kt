package luongvany.k12tt.view.hoiDongQuanTriView.hoiDongQuanTriTableView

import tornadofx.*

class HDQTTableView: View("Hội đồng quản trị"){
    private val centerView: CenterView by inject()
    private val bottomView: BottomView by inject()
    private val rightView: RightView by inject()

    override fun onDock() {
        currentWindow?.width = 1200.0
        currentWindow?.height = 600.0
        currentWindow?.centerOnScreen()
    }

    override val root = borderpane(){
        center = centerView.root
        bottom = bottomView.root
        right = rightView.root
    }

}
