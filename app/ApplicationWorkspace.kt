package luongvany.k12tt.app

import javafx.scene.control.TabPane
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.view.staffview.stafftableview.StaffView
import org.jetbrains.exposed.sql.Database
import tornadofx.*

class ApplicationWorkspace : Workspace("Application", NavigationMode.Tabs) {
    override fun onDock() {
        currentWindow?.width = 1200.0
        currentWindow?.height = 600.0
        currentWindow?.centerOnScreen()
    }
    init {
        //Db
        enableConsoleLogger()
        Database.connect("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver",
                user = "admin", password = "Admin_Password_121")
        //Controller
        StaffController()
        //dock our view
        dock<StaffView>()

        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

    }
}
