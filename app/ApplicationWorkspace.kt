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

    init {

        //Db
        enableConsoleLogger()
        val a = Database.connect("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver",
            user = "root", password = "Hung_Huong_121")

        createTables()

        TransactionManager.current().exec("create user 'testacc'@'localhost' IDENTIFIED BY 'Pass_Word_121';\n")
        TransactionManager.closeAndUnregister(a)
        Database.connect("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver",
                user = "testacc", password = "Pass_Word_121")
        createTables()
        //Controller
        ItemController()
        //dock our view
        dock<StaffView>()

        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        find(LoginView::class).openModal()
    }
}
