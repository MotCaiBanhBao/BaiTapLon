package luongvany.k12tt.controller

import javafx.stage.StageStyle
import javafx.stage.Window
import luongvany.k12tt.app.ApplicationWorkspace
import luongvany.k12tt.model.CurrentDatabase
import luongvany.k12tt.model.datamodel.DepartmentEntryTbl
import luongvany.k12tt.model.datamodel.HoiDongQuanTriEntryTbl
import luongvany.k12tt.model.datamodel.HoiDongQuanTriEntryTbl.maHoiDongQuanTri
import luongvany.k12tt.model.datamodel.LuongEntryTbl
import luongvany.k12tt.model.datamodel.LuongEntryTbl.maLuong
import luongvany.k12tt.model.datamodel.StaffEntryTbl
import luongvany.k12tt.util.*
import luongvany.k12tt.view.homeUI.HomeUi
import luongvany.k12tt.view.initView.ShowProgress
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import tornadofx.Controller

class LoadingController : Controller() {
    private val homeView: HomeUi by inject()
    val mainController: MainController by inject()
    private val showProcess: ShowProgress by inject()
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
                createConnect(CurrentDatabase.User.databaseName)
                showProcess.currentStage?.close()
                homeView.openWindow(StageStyle.DECORATED)
            }
        }
    }
}