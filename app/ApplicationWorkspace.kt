package luongvany.k12tt.app

import javafx.scene.control.TabPane
import tornadofx.*

class ApplicationWorkspace : Workspace("Application", NavigationMode.Tabs) {

    init {
        //Db

        //Controller

        //dock

        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}
