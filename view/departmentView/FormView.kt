package luongvany.k12tt.view.departmentView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.DepartmentController
import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.DepartmentEntryModel
import luongvany.k12tt.view.hoiDongQuanTriView.addHoiDongQT.AddHDQT
import luongvany.k12tt.view.staffView.addStaffView.AddStaff
import tornadofx.*

class FormView(var model: DepartmentEntryModel = DepartmentEntryModel()): View() {
    private val itemController: DepartmentController by inject()
    private val staffController: StaffController by inject()
    private val addStaff: AddStaff by inject()
    private val hdqtController: HDQTController by inject()
    var name = SimpleStringProperty(staffController.listName[0])
    var hdqt = SimpleStringProperty(hdqtController.listName[0])
    private val addHoiDongQuanTri: AddHDQT by inject()
    override val root = form {
        fieldset {
            field("Mã phòng ban") {
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

            field("Tên phòng ban") {
                textfield(model.departmentName) {
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
                    hdqt.onChange{
                        if(it == "+Add hội đồng quản trị")
                            addHoiDongQuanTri.openWindow(owner = null)
                    }
                }
            }

            field("Người đứng đầu") {
                combobox<String>(name) {
                    items = staffController.listName
                    name.onChange{
                        if(it == "+Add Nhân Viên")
                            addStaff.openWindow()
                    }
                }
            }
        }
    }
}