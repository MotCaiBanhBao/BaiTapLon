package luongvany.k12tt.view.hangHoaView

import luongvany.k12tt.controller.HangHoaController
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.HangHoaEntryModel
import tornadofx.*

class Form(val model: HangHoaEntryModel): View("Hàng hóa form") {
    private val itemController: HangHoaController by inject()

    override val root = form {
        fieldset {
            field("Mã hàng hóa") {
                textfield(model.maHangHoa) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            itemController.items.any { itemId -> itemId.maHangHoa.value == it.toInt() } -> error("Id đã trùng")
                            !it.isLong() -> error("Mã nhân viên cần là số")
                            else -> null
                        }
                    }
                }
            }
            field("Tên hàng hóa") {
                textfield(model.ten) {
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
            field("Giới tính") {
                combobox<Sex>(model.gioiTinh) {
                    items = itemController.gioiTinh
                }
            }
            field("Ngày sinh") {
                datepicker(model.namSinh) {
                    this.required()
                    validator {
                        when {
                            it?.dayOfYear.toString().isEmpty() -> error("Tuổi không hợp lệ")
                            else -> null
                        }
                    }
                }
            }
        }
    }
}
