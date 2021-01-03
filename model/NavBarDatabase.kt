package luongvany.k12tt.model

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ListView
import luongvany.k12tt.view.homeUI.NavBar
import luongvany.k12tt.view.homeUI.View1
import luongvany.k12tt.view.homeUI.View2
import tornadofx.View

class NavBarDatabase{
    companion object{
        val listItem = listOf("Nhân viên", "Phòng ban", "Setting")
        var currentItem = SimpleStringProperty(listItem[0])
        var listView = listOf(View1(), View2(), View1())
    }

}