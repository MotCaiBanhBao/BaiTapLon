package luongvany.k12tt.view.hopDongLaoDongDieuKhoan.hDLD_DKTableView

import tornadofx.*

class HDLD_DKView: View("Bảng HDLD_DK"){

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
