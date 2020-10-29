package luongvany.k12tt.view.initview

import javafx.application.Platform
import luongvany.k12tt.app.ApplicationWorkspace
import luongvany.k12tt.controller.LoginController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.newTransaction
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import tornadofx.*
import kotlin.concurrent.thread

class ShowProgress : View("Init application") {
    private val controller: LoginController by inject()
    private val mainController: MainController by inject()
    override val root = vbox {
        enableConsoleLogger()

        progressbar() {
            thread {
                with(newTransaction()) {
                    exec("create database test;")
                    exec("use test;")
                    mainController.listOfObject.forEach{
                        SchemaUtils.create(it)
                    }

                }
                Thread.currentThread().interrupt()
                Platform.runLater{
                    close()
                    TransactionManager.closeAndUnregister(controller.connect!!)
                    find(ApplicationWorkspace::class).openModal()
                }
            }
        }
    }
}



