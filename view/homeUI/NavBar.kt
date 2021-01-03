package luongvany.k12tt.view.homeUI

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.HomeUIController
import luongvany.k12tt.model.NavBarDatabase
import luongvany.k12tt.style.HomeStyle
import tornadofx.*

class NavBar: View(){
    private val homeController: HomeUIController by inject()
    private val homeUi: HomeUi by inject()
    private val view1: View1 by inject()
    private val view2: View2 by inject()
    override val root = vbox{
        hbox {
            style{
                padding = box(5.px, 0.px, 0.px, 0.px)
                backgroundColor += javafx.scene.paint.Color.WHITE
            }
        }
        style{

            backgroundColor += HomeStyle.color
        }
        vbox{
            listmenu(theme = "blue"){
                style{
                    padding = box(5.px)
                    graphicFixedSizeProperty.value = 55.0
                }
                item(graphic = imageview("employees.png"), text = NavBarDatabase.listItem[0]){
                    activeItem = this
                }
                item(graphic = imageview("Department.png"), text = NavBarDatabase.listItem[1]){

                }
                item(graphic = imageview("employees.png"), text = NavBarDatabase.listItem[2]){
                }
                activeItemProperty.addListener{ _, _, newItem ->
                    if (newItem != null) {
                        when(newItem.text){
                            "Nhân viên" -> show(view1)
                            "Phòng ban" -> show(view2)
                            "Setting" -> show(view1)
                        }
                    }

                }
            }
        }
    }
    private fun show(view: View){
        homeUi.root.center = view.root
        NavBarDatabase.currentItem.value = view.titleProperty.value
    }

}
