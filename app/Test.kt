package luongvany.k12tt.app

import javafx.stage.Stage
import luongvany.k12tt.style.LoginStyle
import luongvany.k12tt.view.initView.ShowProgress
import luongvany.k12tt.view.loginView.LoginView
import luongvany.k12tt.view.staffView.staffTableView.StaffView
import org.jetbrains.exposed.sql.Database
import tornadofx.App
import tornadofx.reloadStylesheetsOnFocus
import tornadofx.reloadViewsOnFocus

class Test: App(LoginView::class, LoginStyle::class){

        override fun start(stage: Stage) {
        super.start(stage)
    }

}
