package luongvany.k12tt.view.khachHangView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.KhachHangController
import luongvany.k12tt.model.datamodel.KhachHangEntryModel
import tornadofx.*

class Form(val model: KhachHangEntryModel): View("Khách hàng form") {
    private val itemController: KhachHangController by inject()

    override val root = form {
        fieldset {
            field("Mã khách hàng") {
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
            field("Tên khách hàng") {

                textfield(model.tenKhachHang) {
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
                            else -> null
                        }
                    }
                }
            }
            field("Sở thích") {
                textfield(model.soThich) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            else -> null
                        }
                    }
                }
            }
            field("Số điện thoại") {
                textfield(model.soDienThoai) {
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
