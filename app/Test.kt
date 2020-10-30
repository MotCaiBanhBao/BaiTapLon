package luongvany.k12tt.app

import javafx.stage.Stage
import luongvany.k12tt.style.Style
import luongvany.k12tt.view.departmentview.departmenttableview.DepartmentView
import luongvany.k12tt.view.staffview.stafftableview.StaffView
import org.jetbrains.exposed.sql.Database
import tornadofx.App

class Test: App(StaffView::class, Style::class){
    override fun start(stage: Stage) {
        Database.connect("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver",
                user = "admin", password = "Admin_Password_121")
        with(stage){
            width = 300.0
            height = 300.0
        }

        super.start(stage)
    }
}
