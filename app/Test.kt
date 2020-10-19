package luongvany.k12tt.app

import javafx.stage.Stage
import luongvany.k12tt.style.Style
import luongvany.k12tt.view.staffview.stafftableview.StaffView
import tornadofx.App

class Test: App(ApplicationWorkspace::class, Style::class){
    override fun start(stage: Stage) {
        with(stage){
            width = 1200.0
            height = 600.0
        }

        super.start(stage)
    }
}
