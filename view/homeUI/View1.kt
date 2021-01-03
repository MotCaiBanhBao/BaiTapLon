package luongvany.k12tt.view.homeUI

import javafx.scene.paint.Color
import luongvany.k12tt.view.staffView.staffTableView.StaffView
import tornadofx.*

class View1 : View(){

    private val centerView: StaffView by inject()

    override val root = borderpane{
        style {
            padding = box(0.px, 0.px, 0.px, 5.px)
        }
        top = hbox {
            style{
                padding = box(5.px, 0.px, 0.px, 0.px)
                backgroundColor += Color.WHITE
            }
        }
        center = centerView.root
    }
}