package luongvany.k12tt.view.dieuKhoanView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.DepartmentController
import luongvany.k12tt.controller.DieuKhoanController
import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.DieuKhoanEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.view.departmentView.adddepartment.AddDepartment
import tornadofx.*

class Form(val model: DieuKhoanEntryModel): View("Điều khoản form") {
    private val itemController: DieuKhoanController by inject()

    override val root = form {
        fieldset {
            field("Mã điều khoản") {
                textfield(model.id) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            itemController.items.any { itemId -> itemId.id.value == it.toInt() } -> error("Id đã trùng")
                            !it.isLong() -> error("Mã nhân viên cần là số")
                            else -> null
                        }
                    }
                }
            }
            field("Nội dung") {

                textfield(model.noiDung) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            else -> null
                        }

                    }
              }
            }
        }
    }
}
