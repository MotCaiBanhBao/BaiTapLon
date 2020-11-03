package luongvany.k12tt.view.chucVuView

import luongvany.k12tt.controller.ChucVuController
import luongvany.k12tt.model.datamodel.ChucVuEntryModel
import luongvany.k12tt.view.chucVuView.addChucVuView.AddChucVu
import tornadofx.*

class Form(val model: ChucVuEntryModel): View("Chức vụ form") {
    private val itemController: ChucVuController by inject()
    private val addChucVu: AddChucVu by inject()

    override val root = form {
        fieldset {

            field("Mã chức vụ") {
                textfield(model.maChucVu) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            itemController.items.any { itemId -> itemId.maChucVu.value == it.toInt() } -> error("Id đã trùng")
                            !it.isLong() -> error("Mã nhân viên cần là số")
                            else -> null
                        }
                    }
                }
            }

            field("Tên chúc vụ") {
                textfield(model.tenChucVu) {
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
