package luongvany.k12tt.app

import javafx.stage.Stage
import luongvany.k12tt.style.Style
import luongvany.k12tt.view.homeview.HomeView
import luongvany.k12tt.view.loginview.LoginView
import tornadofx.App

class Test: App(HomeView::class, Style::class){
    override fun start(stage: Stage) {
        with(stage){
            width = 300.0
            height = 300.0
        }

        super.start(stage)
    }
}
