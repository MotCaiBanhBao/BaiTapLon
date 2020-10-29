package luongvany.k12tt.view.staffview.edittableview

import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.StaffEntryModel
import tornadofx.*

class EditTable: View("My View") {
    val model = StaffEntryModel()


    override val root = form {
        fieldset {
            field("Mã nhân viên") {
                textfield(model.id){}
            }
            field("Tên nhân viên") {
                textfield(model.name){}
            }
            field("Quê quán") {
                textfield(model.homeTown){}
            }
            field("Giới tính") {
                combobox<Sex>(model.sex){

                }
            }
            field("Ngày sinh") {
                datepicker(model.birthDay){}
            }
            field("Mã phòng ban") {
                combobox<Number>(model.departmentId){}
            }
            field("Mã Lương") {
                textfield(model.salaryId){}
            }
            field("Hình Ảnh") {
                textfield(model.img){}
            }
            button("Save"){
                action{

                }
            }
        }
    }
}
