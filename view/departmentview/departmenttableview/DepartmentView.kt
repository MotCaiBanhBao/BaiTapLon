package luongvany.k12tt.view.departmentview.departmenttableview

import luongvany.k12tt.view.departmentview.departmenttableview.CenterView
import tornadofx.*

class DepartmentView : View("My View") {
    private val centerView: CenterView by inject()
    private val rightView: RightView by inject()
    private val bottomView: BottomView by inject()
    override val root = borderpane {
        center = centerView.root
        right = rightView.root
        bottom = bottomView.root
    }
}
