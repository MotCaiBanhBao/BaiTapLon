package luongvany.k12tt.view.hopDongView

import luongvany.k12tt.controller.HopDongController
import luongvany.k12tt.model.datamodel.HopDongEntryModel
import tornadofx.*

class Form(val model: HopDongEntryModel): View("Hợp đồng form") {
    private val itemController: HopDongController by inject()

    override val root = form {
        fieldset {
            field("Mã hợp đồng") {
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

            field("Thời gian hạn") {
                datepicker(model.thoiGianThucHien) {
                    this.required()
                    validator {
                        when {
                            else -> null
                        }
                    }
                }
            }
        }
    }
}
