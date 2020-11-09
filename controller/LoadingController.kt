package luongvany.k12tt.controller

import javafx.stage.Window
import luongvany.k12tt.app.ApplicationWorkspace
import luongvany.k12tt.model.CurrentDatabase
import luongvany.k12tt.util.*
import org.jetbrains.exposed.sql.SchemaUtils
import tornadofx.Controller

class LoadingController : Controller() {
    private val applicationWorkspace: ApplicationWorkspace by inject()
    val mainController: MainController by inject()
    fun create(current: Window?) {
        val size = mainController.listOfObject.size.toDouble()-1
        var index = 0.0
       createConnect(CurrentDatabase.User.databaseName)
        runAsync {
            for(i in mainController.listOfObject){
                updateProgress((index), size)
                updateMessage("Create table ${i.tableName}...")
                execute {
                    SchemaUtils.create(i)
                }
                index+=1
            }
            updateMessage("Complete!")
            Thread.sleep(1000)
        }ui{
            try {
                current?.hide()
            }finally {
                disconnectCurrentDbs()
                applicationWorkspace.openWindow()
            }
        }
    }
}