package luongvany.k12tt.view.hDGD_DKView.hDGD_DKTableView

import tornadofx.*

class HDGD_DKView: View("Bảng hợp đồng giao dịch - điều khoản"){

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