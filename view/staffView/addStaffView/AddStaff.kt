package luongvany.k12tt.view.staffView.addStaffView

import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.util.toStaffEntry
import luongvany.k12tt.view.staffView.Form
import tornadofx.*

class AddStaff: View("Thêm nhân viên"){
    private val model = StaffEntryModel()
    private val itemController: StaffController by inject()
    private val mainController: MainController by inject()
    private val luongController: LuongController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        model.salaryId.value = luongController.convertToId(formView.maLuong.value)
                        model.departmentId.value = mainController.convertToId(formView.name, ", Tên: ")
                        itemController.add(model.toStaffEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
