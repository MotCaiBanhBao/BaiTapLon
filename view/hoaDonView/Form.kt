package luongvany.k12tt.view.hoaDonView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.HoaDonController
import luongvany.k12tt.model.datamodel.HoaDonEntryModel
import tornadofx.*

class Form(val model: HoaDonEntryModel): View("Hóa đơn form") {
    private val itemController: HoaDonController by inject()

    override val root = form {
        fieldset {
            field("Mã hóa đơn") {
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
            field("Số tiền") {
                textfield(model.soTien) {}
            }

            field("Ngày lập") {
                datepicker(model.ngayLap) {}
            }
        }
    }
}
