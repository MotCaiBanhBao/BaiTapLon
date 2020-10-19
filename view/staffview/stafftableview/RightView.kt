package luongvany.k12tt.view.staffview.stafftableview

import luongvany.k12tt.controller.StaffViewController
import tornadofx.*

class RightView : View("My View") {
    val leftView: CenterView by inject()
    private val mainController: StaffViewController by inject()

    override val root = hbox{
    }
}
