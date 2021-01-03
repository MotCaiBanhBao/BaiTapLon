package luongvany.k12tt.view.homeUI

import luongvany.k12tt.view.departmentView.departmenttableview.DepartmentView
import luongvany.k12tt.view.staffView.staffTableView.StaffView
import tornadofx.*

class View2 : View(){

    private val centerView: DepartmentView by inject()

    override val root = borderpane{
        style {
            padding = box(0.px, 0.px, 0.px, 5.px)
        }
        top = hbox {
            style{
                padding = box(5.px, 0.px, 0.px, 0.px)
                backgroundColor += javafx.scene.paint.Color.WHITE
            }
        }
        center = centerView.root
    }
}
