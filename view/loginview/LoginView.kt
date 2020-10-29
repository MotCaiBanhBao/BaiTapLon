package luongvany.k12tt.view.loginview

import javafx.geometry.Orientation
import javafx.scene.Scene
import javafx.scene.control.ProgressIndicator
import luongvany.k12tt.controller.LoginController
import luongvany.k12tt.style.Style.Companion.login
import tornadofx.*

class LoginView : View("My View") {

    private val controller: LoginController by inject()
    override val root = form {
        addClass(login)
        fieldset {
            labelPosition = Orientation.VERTICAL
            field("Tên đăng nhập") {
                textfield(controller.nameUser)
            }
            field("Mật khẩu") {
                passwordfield(controller.password)
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
