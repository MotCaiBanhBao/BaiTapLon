package luongvany.k12tt.view.staffview.addstaffview

import luongvany.k12tt.controller.ItemController
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.util.toStaffEntry
import tornadofx.*

class AddStaff: View("Thêm nhân viên"){
    private val model = StaffEntryModel()
    private val itemController: ItemController by inject()

    override val root = form {
        fieldset {
            field("Mã nhân viên") {
                textfield(model.id){
                    this.required()
                    validator {
                        when{
                            it.isNullOrEmpty() || it == " " ->error("Không được để trống")
                            it == "0" ->error("Số phải khác không")
                            itemController.items.any { itemId -> itemId.id.value == it.toInt() } -> error("Id đã trùng")
                            !it.isLong() -> error("Mã nhân viên cần là số")
                            else -> null
                        }
                    }
                }
            }
            field("Tên nhân viên") {
                textfield(model.name){
                    this.required()
                    validator {
                        when{
                            it.isNullOrEmpty() || it == " " ->error("Không được để trống")
                            it.length>30 -> error("Quá dài")
                            else -> null
                        }
                    }
                }
            }
            field("Quê quán") {
                textfield(model.homeTown){
                    this.required()
                    validator {
                        when{
                            it.isNullOrEmpty() || it == " " ->error("Không được để trống")
                            it.length>30 -> error("Quá dài")
                            else -> null
                        }
                    }
                }
            }
            field("Giới tính") {
                combobox<Sex>(model.sex){
                    items = itemController.gioiTinh
                }
            }
            field("Ngày sinh") {
                datepicker(model.birthDay){
                    this.required()
                    validator {
                        when{
                           it?.dayOfYear.toString().isEmpty() -> error("Tuổi không hợp lệ")
                            else -> null
                        }
                    }
                }
            }
            field("Mã phòng ban") {
                combobox<Number>(model.departmentId){}
            }
            field("Mã Lương") {
                textfield(model.salaryId){}
            }
            field("Hình Ảnh") {
                textfield(model.img){}
            }
            button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toStaffEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
}
