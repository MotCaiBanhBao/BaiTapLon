package luongvany.k12tt.view.damNhiemView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.ChucVuController
import luongvany.k12tt.controller.HopDongLaoDongController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.DamNhiemEntryModel
import tornadofx.*

class Form(val model: DamNhiemEntryModel): View("Đảm nhiệm form") {
    private val chucVuController: ChucVuController by inject()
    var chucVu = SimpleStringProperty(chucVuController.listName[0])
    private val hdldController: HopDongLaoDongController by inject()
    var hdld = SimpleStringProperty(hdldController.listName[0])
    private val staffController: StaffController by inject()
    var nhanVien = SimpleStringProperty(staffController.listName[0])

    override val root = form {
        fieldset {
            field("Mã chức vụ") {
                combobox(chucVu) {
                    items = chucVuController.listName
                }
            }
            field("Mã hợp đồng lao động") {
                combobox(hdld){
                    items = hdldController.listName
                }
            }
            field("Ma nhan vien") {
                combobox(nhanVien) {
                    items = staffController.listName
                }
            }
        }
    }
}
