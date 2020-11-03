package luongvany.k12tt.view.hoaDonView.addHoaDonView

import luongvany.k12tt.controller.HoaDonController
import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.HoaDonEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.util.toHoaDonEntry
import luongvany.k12tt.util.toStaffEntry
import luongvany.k12tt.view.staffView.Form
import tornadofx.*

class AddHoaDon: View("Thêm hóa đơn"){
    private val model = HoaDonEntryModel()
    private val itemController: HoaDonController by inject()
    private val formView = luongvany.k12tt.view.hoaDonView.Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toHoaDonEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
