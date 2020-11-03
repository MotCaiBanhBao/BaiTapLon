package luongvany.k12tt.view.dieuKhoanLaoDong

import luongvany.k12tt.controller.DieuKhoanLaoDongController
import luongvany.k12tt.model.datamodel.DieuKhoanLaoDongEntryModel
import luongvany.k12tt.view.dieuKhoanLaoDong.addDKLDView.AddDKLD
import tornadofx.*

class Form(val model: DieuKhoanLaoDongEntryModel): View("Nhân viên form") {
    private val itemController: DieuKhoanLaoDongController by inject()
    private val addDepartment: AddDKLD by inject()

    override val root = form {
        fieldset {
            field("Mã điều khoản lao động") {
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
            field("Nội dung") {

                textfield(model.noiDung) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            else -> null
                        }

                    }
              }
            }
        }
    }
}
