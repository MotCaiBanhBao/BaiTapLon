package luongvany.k12tt.view.staffview.addstaffview

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.image.Image
import luongvany.k12tt.controller.StaffViewController
import luongvany.k12tt.main
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.Staff
import tornadofx.*
import java.time.LocalDate

class AddStaff: View("Thêm nhân viên"){
    private val mainController: StaffViewController by inject()

    override val root = form {
            fieldset {
                field("Mã nhân viên") {
                    textfield(mainController.idStaff){}
                }
                field("Tên nhân viên") {
                    textfield(mainController.name){}
                }
                field("Quê quán") {
                    textfield(mainController.homeTown){}
                }
                field("Giới tính") {
                    combobox<Sex>(mainController.sex){
                        items = mainController.gioiTinh
                    }
                }
                field("Ngày sinh") {
                    datepicker(mainController.birthDay){}
                }
                field("Mã phòng ban") {
                    combobox<String>(mainController.departmentId){}
                }
                field("Mã Lương") {
                    textfield(mainController.salaryId){}
                }
                field("Hình Ảnh") {
                    textfield(mainController.img){}
                }
                button("Save"){
                    shortcut("enter")
                    action{
                        mainController.saveStaff()
                    }
                }
            }
    }
}
