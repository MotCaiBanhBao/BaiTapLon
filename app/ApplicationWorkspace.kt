package luongvany.k12tt.app

import javafx.scene.control.TabPane
import luongvany.k12tt.controller.ItemController
import luongvany.k12tt.util.createTables
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.view.staffview.stafftableview.StaffView
import org.jetbrains.exposed.sql.Database
import tornadofx.*

class ApplicationWorkspace : Workspace("Application", NavigationMode.Tabs) {

    init {
        //Db
        enableConsoleLogger()
        Database.connect("jdbc:sqlite:./app-test.db", "org.sqlite.JDBC")
        createTables()

        ItemController()
        //Controller

        //dock our view
        dock<StaffView>()

        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}
