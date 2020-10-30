package luongvany.k12tt.view.staffview.addstaffview

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.DepartmentController
import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.util.toStaffEntry
import luongvany.k12tt.view.staffview.Form
import tornadofx.*

class AddStaff: View("Thêm nhân viên"){
    private val model = StaffEntryModel()
    private val itemController: StaffController by inject()
    private val departmentController: DepartmentController by inject()
    private val luongController: LuongController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        model.salaryId.value = luongController.convertToId(formView.maLuong.value)
                        model.departmentId.value = departmentController.convertToId(formView.name)
                        itemController.add(model.toStaffEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
