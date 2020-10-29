package luongvany.k12tt.controller

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.app.ApplicationWorkspace
import luongvany.k12tt.util.*
import luongvany.k12tt.view.initview.ShowProgress
import luongvany.k12tt.view.loginview.LoginView
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import tornadofx.*

class LoginController : Controller(){
    val nameUser = SimpleStringProperty()
    val password = SimpleStringProperty()

    val loginView: LoginView by inject()
    var connect: Database? = null
    fun login(){
        connect = Database.connect("jdbc:mysql://localhost:3306/", driver = "com.mysql.jdbc.Driver",
                user = nameUser.value, password = password.value)

        if (isConnected()){
            if(!isInit()){
                warning(title = "Khởi tạo", header = "Chưa khởi tạo", content = "Đây là lần đầu đăng nhập của bạn\n" +
                        "Mời bạn khởi tạo lần đầu")
                loginView.close()
                find(ShowProgress::class).openWindow()
            }
            else{
                information("Chào mừng đã trở lại chương trình")
                find(ApplicationWorkspace::class).openModal()
            }
        }
        else{
            error("Sai tên đăng nhập hoặc mật khẩu\nVui lòng liên hệ admin")
            TransactionManager.closeAndUnregister(connect!!)
        }

    }
}