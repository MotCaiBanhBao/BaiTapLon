package luongvany.k12tt.view.phuCapView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.*
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.PhuCapEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.view.departmentView.adddepartment.AddDepartment
import luongvany.k12tt.view.phuCapView.addPhuCapView.AddPhuCap
import luongvany.k12tt.view.thanhVienHDQT.addThanhVien.AddThanhVienView
import tornadofx.*

class Form(val model: PhuCapEntryModel): View("Nhân viên form") {
    private val itemController: PhuCapController by inject()
    private val thanhVienHDQTController: ThanhVienHDQTController  by inject()
    var name = SimpleStringProperty(thanhVienHDQTController.listName[0])
    private val addThanhVienHDQ: AddThanhVienView by inject()

    override val root = form {
        fieldset {
            field("Mã phụ cấp") {
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
            field("Thành viên được hưởng") {
                combobox(name){
                    items = thanhVienHDQTController.listName
                    name.onChange {
                        if(it == "+Add new thành viên"){
                            addThanhVienHDQ.openModal(owner = null)
                        }
                    }
                }
            }
            field("Thông tin phụ cấp") {
                textfield(model.thongTinPhuCap) {
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
            field("Ngày được hưởng") {
                datepicker(model.ngayDuocHuong) {
                }
            }
        }
    }
}
