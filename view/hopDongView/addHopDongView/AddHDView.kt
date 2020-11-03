package luongvany.k12tt.view.hopDongView.addHopDongView

import luongvany.k12tt.controller.HopDongController
import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.HopDongEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.util.toHopDongEntry
import luongvany.k12tt.util.toStaffEntry
import luongvany.k12tt.view.staffView.Form
import tornadofx.*

class AddHDView: View("Thêm hợp đồng"){
    private val model = HopDongEntryModel()
    private val itemController: HopDongController by inject()
    private val formView = luongvany.k12tt.view.hopDongView.Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toHopDongEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
