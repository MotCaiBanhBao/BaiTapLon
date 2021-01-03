package luongvany.k12tt.controller

import javafx.beans.property.ObjectProperty
import luongvany.k12tt.model.NavBarDatabase
import luongvany.k12tt.view.homeUI.HomeUi
import luongvany.k12tt.view.homeUI.View1
import luongvany.k12tt.view.homeUI.View2
import tornadofx.Controller
import tornadofx.ListMenuItem
import tornadofx.View

class HomeUIController: Controller() {
    private val homeUi: HomeUi by inject()

    fun change(index: Int) {
       NavBarDatabase.currentItem.value = NavBarDatabase.listItem[index]
        when (index) {
            0 -> {
                homeUi.root.center = View1().root
            }
            1 -> {
                homeUi.root.center = View2().root
            }
            else -> {
                homeUi.root.center = View1().root
            }
        }
    }

}