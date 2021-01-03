package luongvany.k12tt.app

import javafx.stage.Stage
import luongvany.k12tt.style.LoginStyle
import luongvany.k12tt.view.homeUI.HomeUi
import luongvany.k12tt.view.loginView.LoginView
import tornadofx.App

class Test: App(HomeUi::class, LoginStyle::class){
        override fun start(stage: Stage) {
        super.start(stage)
    }

}
