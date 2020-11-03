package luongvany.k12tt.view.hDGD_DKView

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.*
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.HDGD_DKEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.view.departmentView.adddepartment.AddDepartment
import luongvany.k12tt.view.dieuKhoanView.addDieuKhoanView.AddDieuKhoan
import luongvany.k12tt.view.hopDongView.addHopDongView.AddHDView
import tornadofx.*

class Form(val model: HDGD_DKEntryModel): View("Hợp đồng giao dịch điều khoản form") {
    private val hopDongController: HopDongController by inject()
    var hopDong = SimpleStringProperty(hopDongController.listName[0])
    private val dieuKhoanController: DieuKhoanController by inject()
    val dieuKhoan = SimpleStringProperty(dieuKhoanController.listName[0])
    private val addHopDong: AddHDView by inject()
    private val addDieuKhoan: AddDieuKhoan by inject()

    override val root = form {
        fieldset {
            field("Mã hợp đồng") {
                combobox(hopDong) {

                    items = hopDongController.listName
                    hopDong.onChange {
                        if(it == "+Add new hợp đồng"){
                           addHopDong.openModal(owner = null)
                        }
                    }
                }
            }

            field("Điều khoản") {
                combobox(dieuKhoan){

                    items = dieuKhoanController.listName
                    dieuKhoan.onChange {
                        if(it == "+Add điều khoản lao động"){
                            addDieuKhoan.openModal(owner = null)
                        }
                    }
                }
            }
        }
    }
}
