package luongvany.k12tt.view.staffview.addstaffview

import luongvany.k12tt.controller.StaffViewController
import luongvany.k12tt.model.Sex
import tornadofx.*

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
