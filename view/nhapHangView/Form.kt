package luongvany.k12tt.view.nhapHangView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.*
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.NhapHangEntryModel
import luongvany.k12tt.view.doiTacView.addDoiTacView.AddDoiTac
import luongvany.k12tt.view.hangHoaView.addHangHoaView.AddHangHoa
import luongvany.k12tt.view.hoaDonView.addHoaDonView.AddHoaDon
import luongvany.k12tt.view.staffView.addStaffView.AddStaff
import tornadofx.*

class Form(val model: NhapHangEntryModel): View("Nhập hàng form") {
    private val itemController: NhapHangController by inject()
    private val staffController: StaffController by inject()
    var staff = SimpleStringProperty(staffController.listName[0])
    private val doiTacController: DoiTacController by inject()
    var doiTac = SimpleStringProperty(doiTacController.listName[0])
    private val hoaDonController: HoaDonController by inject()
    var hoaDon = SimpleStringProperty(hoaDonController.listName[0])
    private val hangHoaController: HangHoaController by inject()
    var hangHoa = SimpleStringProperty(hangHoaController.listName[0])
    private val addHoaDon: AddHoaDon by inject()
    private val addNhanVien: AddStaff by inject()
    private val addHangHoa: AddHangHoa by inject()
    private val addDoiTac: AddDoiTac by inject()

    override val root = form {
        fieldset {
            field("Mã nhập hàng") {
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
            field("Nhân viên nhập") {
                combobox(staff) {
                    staffController.listName.add("+Add new nhân viên")
                    items = staffController.listName

                    staff.onChange{
                        if(it == "+Add new nhân viên")
                            addNhanVien.openModal(owner = null)
                    }
                }
            }
            field("Mã hóa đơn") {
                combobox(hoaDon) {
                    hoaDonController.listName.add("+Add new hóa đơn")
                    items = hoaDonController.listName

                    hoaDon.onChange{
                        if(it == "+Add new hóa đơn")
                            addHoaDon.openModal(owner = null)
                    }
                }
            }
            field("Mã hàng hóa") {
                combobox(hangHoa){
                    hangHoaController.listName.add("+Add new hàng hóa")
                    items = hangHoaController.listName
                    hangHoa.onChange {
                        if(it == "+Add new hàng hóa"){
                            addHangHoa.openModal(owner = null)
                        }
                    }
                }
            }
            field("Mã đối tác") {
                combobox(doiTac){
                    doiTacController.listName.add("+Add new đối tác")
                    items = doiTacController.listName
                    doiTac.onChange {
                        if(it == "+Add new đối tác"){
                            addDoiTac.openModal(owner = null)
                        }
                    }
                }
            }
        }
    }
}
