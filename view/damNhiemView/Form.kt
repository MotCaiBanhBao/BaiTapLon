package luongvany.k12tt.view.damNhiemView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.ChucVuController
import luongvany.k12tt.controller.HopDongLaoDongController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.DamNhiemEntryModel
import luongvany.k12tt.view.chucVuView.addChucVuView.AddChucVu
import luongvany.k12tt.view.hDLDView.addHopDongLaoDongView.AddHDLD
import luongvany.k12tt.view.staffView.addStaffView.AddStaff
import tornadofx.*

class Form(val model: DamNhiemEntryModel): View("Đảm nhiệm form") {
    private val chucVuController: ChucVuController by inject()
    var chucVu = SimpleStringProperty(chucVuController.listName[0])
    private val hdldController: HopDongLaoDongController by inject()
    var hdld = SimpleStringProperty(hdldController.listName[0])
    private val staffController: StaffController by inject()
    var nhanVien = SimpleStringProperty(staffController.listName[0])
    private val addChucVu: AddChucVu by inject()
    private val addHopDongLD: AddHDLD by inject()
    private val addStaff: AddStaff by inject()

    override val root = form {
        fieldset {
            field("Mã chức vụ") {
                combobox(chucVu) {
                    items = chucVuController.listName
                    chucVu.onChange{
                        if(it == "+Add chức vụ")
                            addChucVu.openWindow(owner = null)
                    }
                }
            }
            field("Mã hợp đồng lao động") {
                combobox(hdld){
                    items = hdldController.listName
                    hdld.onChange{
                        if(it == "+Add hợp đồng lao động")
                            addHopDongLD.openWindow(owner = null)
                    }
                }
            }
            field("Mã nhân viên") {
                combobox(nhanVien) {

                    items = staffController.listName
                    nhanVien.onChange{
                        if(it == "+Add Nhân Viên")
                            addStaff.openWindow(owner = null)
                    }
                }
            }
        }
    }
}
