package luongvany.k12tt.controller

import javafx.stage.StageStyle
import luongvany.k12tt.app.ApplicationWorkspace
import luongvany.k12tt.model.CurrentDatabase
import luongvany.k12tt.util.*
import luongvany.k12tt.view.homeUI.HomeUi
import luongvany.k12tt.view.initView.ShowProgress
import luongvany.k12tt.view.loginView.LoginView
import org.jetbrains.exposed.sql.Database
import tornadofx.*

class LoginController : Controller(){

    private val loginView: LoginView by inject()
    val mainController: MainController by inject()
    private val showProgress: ShowProgress by inject()
    private val homeUi: HomeUi by inject()
    fun login(){
        if (isConnected()){
            if(!isInit()){
                warning(title = "Khởi tạo", header = "Chưa khởi tạo", content = "Đây là lần đầu đăng nhập của bạn\n" +
                        "Mời bạn khởi tạo lần đầu")
                loginView.currentStage?.close()
                createDatabase(CurrentDatabase.User.databaseName)
                showProgress.openWindow(stageStyle = StageStyle.UNDECORATED)
            }
            else{
                disconnectCurrentDbs()
                information("Chào mừng đã trở lại chương trình")
                createConnect("hallo")
                loginView.currentStage?.close()
                showProgress.openWindow(StageStyle.UNDECORATED)
            }
        }
        else{
            error("Sai tên đăng nhập hoặc mật khẩu\nVui lòng liên hệ admin")
        }
    }
}