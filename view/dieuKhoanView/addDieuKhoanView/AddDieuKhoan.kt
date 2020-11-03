package luongvany.k12tt.view.dieuKhoanView.addDieuKhoanView

import luongvany.k12tt.controller.DieuKhoanController
import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.DieuKhoanEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.util.toDieuKhoanEntry
import luongvany.k12tt.util.toStaffEntry
import luongvany.k12tt.view.staffView.Form
import tornadofx.*

class AddDieuKhoan: View("Thêm điều viên"){
    private val model = DieuKhoanEntryModel()
    private val itemController: DieuKhoanController by inject()
    private val formView = luongvany.k12tt.view.dieuKhoanView.Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toDieuKhoanEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
