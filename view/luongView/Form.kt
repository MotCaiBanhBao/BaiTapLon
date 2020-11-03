package luongvany.k12tt.view.luongView

import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.model.datamodel.LuongEntryModel
import tornadofx.*

class Form(val model: LuongEntryModel): View("Lương form") {
    private val itemController: LuongController by inject()

    override val root = form {
        fieldset {
            field("Mã lương") {
                textfield(model.id) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            itemController.items.any { itemId -> itemId.id.value == it.toInt() } -> error("Id đã trùng")
                            !it.isLong() -> error("Mã cần là số")
                            else -> null
                        }
                    }
                }
            }
            field("Bậc lương"){
                textfield(model.bacLuong) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            !it.isLong() -> error("Mã cần là số")
                            else -> null
                        }
                    }
                }
            }
            field("Lương cơ bản") {
                textfield(model.luongCoBan){
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            !it.isLong() -> error("Mã cần là số")
                            else -> null
                        }
                    }
                }
            }
            field("Hệ số lương"){
                textfield(model.heSoLuong){
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            !it.isLong() -> error("Mã cần là số")
                            else -> null
                        }
                    }
                }
            }
        }
    }
}
