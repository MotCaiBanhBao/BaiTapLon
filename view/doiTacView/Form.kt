package luongvany.k12tt.view.doiTacView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.DoiTacController
import luongvany.k12tt.model.datamodel.DoiTacEntryModel
import tornadofx.*

class Form(val model: DoiTacEntryModel): View("Đối tác form") {
    private val itemController: DoiTacController by inject()

    override val root = form {
        fieldset {
            field("Đôi tác") {
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
            field("Tên đối tác") {

                textfield(model.name) {
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
            field("Địa chỉ") {
                textfield(model.diaChi) {
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
        }
    }
}
