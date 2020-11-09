package luongvany.k12tt.view.homeUI

import luongvany.k12tt.style.HomeStyle
import luongvany.k12tt.style.HomeStyle.Companion.centerView
import luongvany.k12tt.style.HomeStyle.Companion.leftView
import luongvany.k12tt.view.homeView.HomeView
import tornadofx.*

class HomeUi : View(){

    override fun onDock() {
        currentStage?.isMaximized = true
    }
    override val root = borderpane{
        left = listmenu(theme = "blue"){
            style{
                graphicFixedSizeProperty.value = 100.0
                padding = box(10.px)
            }
            item(graphic = imageview("employees.png"))

            item(graphic = imageview("employees.png"))
            item(graphic = imageview("employees.png"))
        }
    }
}