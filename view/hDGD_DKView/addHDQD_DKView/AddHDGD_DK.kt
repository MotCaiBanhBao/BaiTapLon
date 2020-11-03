package luongvany.k12tt.view.hDGD_DKView.addHDQD_DKView

import luongvany.k12tt.controller.HDGD_DKController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.model.datamodel.HDGD_DKEntryModel
import luongvany.k12tt.util.toHDGD_DKEntry
import luongvany.k12tt.view.hDGD_DKView.Form
import tornadofx.*

class AddHDGD_DK: View("Thêm hợp đồng giao dịch -  điều khỏa "){
    private val model = HDGD_DKEntryModel()
    private val itemController: HDGD_DKController by inject()
    private val mainController: MainController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        model.maDieuKhoan.value = mainController.convertToId(formView.dieuKhoan, ", Điều khoản: ")
                        model.maHopDong.value = mainController.convertToId(formView.hopDong, ", Thòi gian thực hiện: ")
                        itemController.add(model.toHDGD_DKEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
