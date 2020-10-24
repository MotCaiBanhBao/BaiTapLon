package luongvany.k12tt.app

import javafx.scene.control.TabPane
import luongvany.k12tt.controller.ItemController
import luongvany.k12tt.model.DepartmentEntry
import luongvany.k12tt.model.DepartmentEntryTbl
import luongvany.k12tt.model.StaffEntryTbl
import luongvany.k12tt.util.createTables
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import luongvany.k12tt.view.staffview.stafftableview.StaffView
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import org.joda.time.LocalDate
import tornadofx.*
import java.sql.Date

class ApplicationWorkspace : Workspace("Application", NavigationMode.Tabs) {

    init {
        //Db
        enableConsoleLogger()
        Database.connect("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver",
            user = "root", password = "Hung_Huong_121")
        createTables()
        execute{
            StaffEntryTbl.insert {
                it[id] = 1
                it[name] = "Nguyễn Văn A"
                it[sex] = "NAM"
                it[homeTown] = "Viet nam"
                it[birthDay] = DateTime.now()
                it[departmentId] = null
                it[salaryId] = 1
                it[img] = "person-male.png"
            }
            DepartmentEntryTbl.insert {
                it[departmentId] = 1
                it[departmentName] = "Phòng nhân sự"
                it[managerId] = null
                it[directorateId] = 1
            }
            StaffEntryTbl.update ({
                StaffEntryTbl.id eq 1
            }){
                it[departmentId] = 1
            }

            DepartmentEntryTbl.update ({
                 DepartmentEntryTbl.departmentId eq 1
            }){
                it[managerId] = 1
            }
        }
        //Controller
        ItemController()
        //dock our view
        dock<StaffView>()
        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}
