package luongvany.k12tt.controller

import luongvany.k12tt.app.ApplicationWorkspace
import luongvany.k12tt.model.User
import luongvany.k12tt.util.*
import luongvany.k12tt.view.initView.ShowProgress
import luongvany.k12tt.view.loginView.LoginView
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import tornadofx.*

class LoginController : Controller(){

    val loginView: LoginView by inject()
    var connect: Database? = null
    fun login(){
        connect = Database.connect("jdbc:mysql://localhost:3306/", driver = "com.mysql.jdbc.Driver",
                user = User.userName.value, password = User.password.value)

        if (isConnected()){
            if(!isInit()){
                warning(title = "Khởi tạo", header = "Chưa khởi tạo", content = "Đây là lần đầu đăng nhập của bạn\n" +
                        "Mời bạn khởi tạo lần đầu")
                loginView.close()
                find(ShowProgress::class).openWindow()
            }
            else{
                TransactionManager.closeAndUnregister(connect!!)
                information("Chào mừng đã trở lại chương trình")
                loginView.close()
                find(ApplicationWorkspace::class).openModal()
            }
        }
        else{
            error("Sai tên đăng nhập hoặc mật khẩu\nVui lòng liên hệ admin")
            TransactionManager.closeAndUnregister(connect!!)
        }

    }
}