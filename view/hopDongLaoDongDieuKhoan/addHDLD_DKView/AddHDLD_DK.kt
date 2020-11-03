package luongvany.k12tt.view.hopDongLaoDongDieuKhoan.addHDLD_DKView

import luongvany.k12tt.controller.HDLD_DKController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.model.datamodel.HDLD_DKEntryModel
import luongvany.k12tt.util.toHDLD_DKEntry
import tornadofx.*

class AddHDLD_DK: View("Thêm nhân viên"){
    private val model = HDLD_DKEntryModel()
    private val itemController: HDLD_DKController by inject()
    private val mainController: MainController by inject()
    private val formView = luongvany.k12tt.view.hopDongLaoDongDieuKhoan.Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        model.maHDLD.value = mainController.convertToId(formView.hopDongLaoDong, ", Ngày thành lập: ")
                        model.maDieuKhoan.value = mainController.convertToId(formView.dieuKhoanLaoDong, ", Nội dung: ")
                        itemController.add(model.toHDLD_DKEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
