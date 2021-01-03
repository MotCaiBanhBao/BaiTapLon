package luongvany.k12tt.view.homeUI

import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import luongvany.k12tt.app.ApplicationWorkspace
import luongvany.k12tt.model.CurrentDatabase
import luongvany.k12tt.model.NavBarDatabase
import luongvany.k12tt.model.datamodel.DepartmentEntryTbl
import luongvany.k12tt.model.datamodel.HoiDongQuanTriEntryTbl
import luongvany.k12tt.model.datamodel.LuongEntryTbl
import luongvany.k12tt.model.datamodel.StaffEntryTbl
import luongvany.k12tt.style.HomeStyle
import luongvany.k12tt.util.createConnect
import luongvany.k12tt.util.execute
import luongvany.k12tt.view.staffView.Form
import luongvany.k12tt.view.staffView.staffTableView.StaffView
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import tornadofx.*
import java.awt.Label
import javax.swing.text.TableView
import kotlin.properties.Delegates

class HomeUi : View(){
    private val navBar: NavBar by inject()
    private val centerElement: View1 by inject()
    init{
        createConnect("hallo")
    }
    override fun onDock() {

        currentStage?.isMaximized = true
    }
    override val root = borderpane{
        style{
            padding = box(5.px)
            backgroundColor += c("white")
        }
        top = vbox{
            style{
                prefHeight = 50.px
                backgroundColor += HomeStyle.color
            }
            label(NavBarDatabase.currentItem).apply {
                style{
                    textFill = c("white")
                    fontSize = 20.px
                    padding = box(15.px, 0.px, 0.px, 10.px)
                }
            }

        }
        left = navBar.root
        center = centerElement.root
    }
}