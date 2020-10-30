package luongvany.k12tt.view.departmentview

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.DepartmentController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.DepartmentEntryModel
import tornadofx.*

class FormView(var model: DepartmentEntryModel = DepartmentEntryModel()): View() {
    private val itemController: DepartmentController by inject()
    private val staffController: StaffController by inject()
    var name = SimpleStringProperty(staffController.listName[0])
    override val root = form {
        fieldset {
            field("Mã phòng ban") {
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
            field("Tên phòng ban") {
                textfield(model.departmentName) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it.length > 30 -> error("Quá dài")
                            else -> null
                        }
                    }

                }
            }
            field("Trực thuộc hội đồng") {
                textfield(model.directorateId) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it.length > 30 -> error("Quá dài")
                            else -> null
                        }
                    }
                }
            }
            field("Người đứng đầu") {
                combobox<String>(name) {
                    items = staffController.listName
                }
            }
        }
    }
}