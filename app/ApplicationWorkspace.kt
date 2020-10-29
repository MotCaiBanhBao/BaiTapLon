package luongvany.k12tt.app

import javafx.scene.control.TabPane
import luongvany.k12tt.controller.ItemController
import luongvany.k12tt.util.createTables
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.view.loginview.LoginView
import luongvany.k12tt.view.staffview.stafftableview.StaffView
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
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
        ItemController()
        //dock our view
        dock<StaffView>()

        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

    }
}
