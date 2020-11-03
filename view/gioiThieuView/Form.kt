package luongvany.k12tt.view.gioiThieuView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.*
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.GioiThieuEntryModel
import luongvany.k12tt.view.hangHoaView.addHangHoaView.AddHangHoa
import luongvany.k12tt.view.hopDongView.addHopDongView.AddHDView
import luongvany.k12tt.view.khachHangView.addKhachHangView.AddKhachHang
import luongvany.k12tt.view.staffView.addStaffView.AddStaff
import tornadofx.*

class Form(val model: GioiThieuEntryModel): View("Giới thiệu form") {
    private val itemController: GioiThieuController by inject()
    private val staffController: StaffController by inject()
    var staff = SimpleStringProperty(staffController.listName[0])
    private val hopDongController: HopDongController by inject()
    val hopDong = SimpleStringProperty(hopDongController.listName[0])
    private val hangHoaController: HangHoaController by inject()
    val hangHoa = SimpleStringProperty(hangHoaController.listName[0])
    private val khachHangController: KhachHangController by inject()
    val khachHang = SimpleStringProperty(khachHangController.listName[0])
    private val addStaff: AddStaff by inject()
    private val addHopDong: AddHDView by inject()
    private val addHangHoa: AddHangHoa by inject()
    private val addKhachHang: AddKhachHang by inject()

    override val root = form {
        fieldset {
            field("Mã giới thiêu") {
                textfield(model.maGioiThieu) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            itemController.items.any { itemId -> itemId.maGioiThieu.value == it.toInt() } -> error("Id đã trùng")
                            !it.isLong() -> error("Mã nhân viên cần là số")
                            else -> null
                        }
                    }
                }
            }
            field("Mã nhân viên") {
                combobox(staff) {
                    staffController.listName.add("+Add new nhân viên")
                    items = staffController.listName

                    staff.onChange{
                        if(it == "+Add new nhân viên")
                            addStaff.openWindow(owner = null)
                    }
                }
            }
            field("Mã hợp đồng") {
                combobox(hopDong) {
                    hopDongController.listName.add("+Add new hợp đồng")
                    items = hopDongController.listName

                    hopDong.onChange{
                        if(it == "+Add new hợp đồng")
                            addHopDong.openWindow(owner = null)
                    }
                }
            }
            field("Mã hàng hóa") {
                combobox(hangHoa) {
                    hangHoaController.listName.add("+Add new hàng hóa")
                    items = hangHoaController.listName

                    hangHoa.onChange{
                        if(it == "+Add new hàng hóa")
                            addHangHoa.openWindow(owner = null)
                    }
                }
            }
            field("Mã khách hàng") {
                combobox(khachHang) {
                    khachHangController.listName.add("+Add new khách hàng")
                    items = khachHangController.listName

                    khachHang.onChange{
                        if(it == "+Add new khách hàng")
                            addKhachHang.openWindow(owner = null)
                    }
                }
            }
        }
    }
}
