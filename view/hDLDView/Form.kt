package luongvany.k12tt.view.hDLDView

import luongvany.k12tt.controller.HopDongLaoDongController
import luongvany.k12tt.model.datamodel.HopDongLaoDongEntryModel
import tornadofx.*

class Form(val model: HopDongLaoDongEntryModel): View("Hợp đồng form") {
    private val itemController: HopDongLaoDongController by inject()

    override val root = form {
        fieldset {
            field("Mã hợp đồng lao động") {
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

            field("Ngày thành lập") {
                datepicker(model.ngayThanhLap) {
                    this.required()
                }
            }
        }
    }
}
