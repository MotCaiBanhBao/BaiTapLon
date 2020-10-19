package luongvany.k12tt.view.staffview.edittableview

import luongvany.k12tt.controller.StaffViewController
import luongvany.k12tt.model.Sex
import tornadofx.*

class EditTable: View("My View") {
    private val mainController: StaffViewController by inject()

    override val root = form {
        fieldset {
            field("Mã nhân viên") {
                textfield(mainController.staffDatas[mainController.index].idProperty){}
            }
            field("Tên nhân viên") {
                textfield(mainController.staffDatas[mainController.index].nameProperty){}
            }
            field("Quê quán") {
                textfield(mainController.staffDatas[mainController.index].homeTownProperty){}
            }
            field("Giới tính") {
                combobox<Sex>(mainController.staffDatas[mainController.index].sexProperty){
                    items = mainController.gioiTinh
                }
            }
            field("Ngày sinh") {
                datepicker(mainController.staffDatas[mainController.index].birthDayProperty){}
            }
            field("Mã phòng ban") {
                combobox<String>(mainController.departmentId){}
            }
            field("Mã Lương") {
                textfield(mainController.staffDatas[mainController.index].salaryIdProperty){}
            }
            field("Hình Ảnh") {
                textfield(mainController.staffDatas[mainController.index].imgProperty.value.url){}
            }
            button("Save"){
                action{

                }
            }
        }
    }
}
