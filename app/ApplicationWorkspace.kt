package luongvany.k12tt.app

import javafx.scene.control.TabPane
import javafx.scene.image.Image
import luongvany.k12tt.controller.ItemController
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.Staff
import luongvany.k12tt.model.StaffEntryTbl
import luongvany.k12tt.util.createTables
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import tornadofx.*
import java.time.LocalDate

class ApplicationWorkspace : Workspace("Application", NavigationMode.Tabs) {

    init {
        //Db
        enableConsoleLogger()
        Database.connect("jdbc:sqlite:./app-test.db", "org.sqlite.JDBC")
        createTables()

        ItemController()
        //Controller

        //dock

        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}
