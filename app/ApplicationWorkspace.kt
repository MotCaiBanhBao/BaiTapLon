package luongvany.k12tt.app

import javafx.scene.control.TabPane
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.view.staffView.staffTableView.StaffView
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

        StaffController()
//        execute {
//            LuongEntryTbl.insert {
//                it[maLuong] = 1
//                it[bacLuong] = "2"
//                it[luongCoBan] = 10000
//                it[heSoLuong] = "1,2"
//            }
//            StaffEntryTbl.insert {
//                it[id] = 1
//                it[name] = "Nguyễn Văn A"
//                it[sex] = "NAM"
//                it[homeTown] = "Viet nam"
//                it[birthDay] = DateTime.now()
//                it[departmentId] = null
//                it[salaryId] = 1
//                it[img] = "person-male.png"
//            }
//            HoiDongQuanTriEntryTbl.insert {
//                it[maHoiDongQuanTri] = 1
//                it[nhiemKy] = "4 nam"
//            }
//            DepartmentEntryTbl.insert {
//                it[departmentId] = 1
//                it[departmentName] = "Nhân Sự"
//                it[managerId] = 1
//                it[directorateId] = 1
//            }
//            StaffEntryTbl.update ({
//                StaffEntryTbl.id eq 1
//            }){
//                it[departmentId] = 1
//            }
//        }

        //Controller

        //dock our view
        dock<StaffView>()

        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

    }
}
