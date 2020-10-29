package luongvany.k12tt.view.loginview

import javafx.geometry.Orientation
import javafx.scene.Scene
import javafx.scene.control.ProgressIndicator
import luongvany.k12tt.controller.LoginController
import luongvany.k12tt.model.User
import luongvany.k12tt.style.Style.Companion.login
import tornadofx.*

class LoginView : View("My View") {

    private val controller: LoginController by inject()
    override val root = form {
        addClass(login)
        fieldset {
            labelPosition = Orientation.VERTICAL
            field("Tên đăng nhập") {
                textfield(User.userName)
            }
            field("Mật khẩu") {
                passwordfield(User.password)
            }
        }
        button("Login"){
            action{
                graphic = ProgressIndicator()
                controller.login()
            }
        }
    }
}
