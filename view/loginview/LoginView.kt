package luongvany.k12tt.view.loginview

import javafx.geometry.Pos
import luongvany.k12tt.model.Sex
import luongvany.k12tt.util.toStaffEntry
import tornadofx.*
import tornadofx.Form

class LoginView : Fragment("My View") {
    override val root = form {
        fieldset {
            field("Tên đăng nhập") {
                textfield(){
                }
            }
            field("Mật khẩu") {
                textfield(){

                }

            }

        }
    }

}
