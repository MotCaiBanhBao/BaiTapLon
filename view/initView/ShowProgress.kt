package luongvany.k12tt.view.initView

import javafx.application.Platform
import luongvany.k12tt.app.ApplicationWorkspace
import luongvany.k12tt.controller.LoadingController
import luongvany.k12tt.controller.LoginController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.style.InitStyle.Companion.progressBarStyle
import luongvany.k12tt.util.enableConsoleLogger
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import tornadofx.*
import kotlin.concurrent.thread

class ShowProgress : View("Init application") {
    private val loadingController: LoadingController by inject()
    override fun onDock() {
        currentStage?.height = 700.0
        currentStage?.width = 751.0
        currentStage?.centerOnScreen()
        loadingController.create(currentWindow)
    }

    private val status: TaskStatus by inject()
    override val root = borderpane() {
        center = imageview("BackgroundLoading.png")

        bottom = borderpane{

            top = label(status.message)
            center = progressbar(status.progress) {
                style{
                    progressBarStyle
                    prefHeight = 20.px
                    prefWidth = 751.px
                    accentColor = c("red")
                }
            }
        }
    }
}



