package luongvany.k12tt.view.staffview.stafftableview

import tornadofx.*

class StaffView: View("Bảng nhân viên"){
    private val bottomView: BottomView by inject()
    private val centerView: CenterView by inject()
    private val rightView: RightView by inject()

    override val root = borderpane(){
        center = centerView.root
        right = rightView.root
        bottom = bottomView.root
    }

}
