package luongvany.k12tt.view.departmentView.departmenttableview

import tornadofx.*

class DepartmentView : View("My View") {
    override fun onDock() {
        currentWindow?.width = 1200.0
        currentWindow?.height = 600.0
        currentWindow?.centerOnScreen()
    }
    private val centerView: CenterView by inject()
    private val rightView: RightView by inject()
    private val bottomView: BottomView by inject()
    override val root = borderpane {
        center = centerView.root
        right = rightView.root
        bottom = bottomView.root
    }
}
