package luongvany.k12tt.view.thanhVienHDQT

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.model.datamodel.ThanhVienHDQTEntryModel
import luongvany.k12tt.view.hoiDongQuanTriView.addHoiDongQT.AddHDQT
import tornadofx.*

class Form(val model:ThanhVienHDQTEntryModel): View("Thành viên hdqt fỏm") {
    private val hdqtController: HDQTController by inject()
    var hdqt = SimpleStringProperty(hdqtController.listName[0])
    private val addHDQT: AddHDQT by inject()

    override val root = form {
        fieldset {
            field("Mã thành viên") {
                textfield(model.id) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            !it.isLong() -> error("Mã  cần là số")
                            else -> null
                        }
                    }
                }
            }
            field("Tên thành viên") {
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
            field("Số chứng minh nhân dân"){
                textfield(model.soChungMinh) {
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
                combobox<String>(hdqt) {
                    items = hdqtController.listName
                    hdqt.onChange {
                        if(it == "+Add hội đồng quản trị"){
                            addHDQT.openModal(owner = null)
                        }
                    }
                }
            }
        }
    }
}
