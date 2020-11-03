package luongvany.k12tt.app

import javafx.stage.Stage
import luongvany.k12tt.style.Style
import luongvany.k12tt.view.chucVuView.chuVuTableView.ChucVuView
import luongvany.k12tt.view.damNhiemView.damNhiemTableView.DamNhiemView
import org.jetbrains.exposed.sql.Database
import tornadofx.App

class Test: App(DamNhiemView::class, Style::class){
    override fun start(stage: Stage) {
        Database.connect("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver",
                user = "admin", password = "Admin_Password_121")
        super.start(stage)
    }
}
