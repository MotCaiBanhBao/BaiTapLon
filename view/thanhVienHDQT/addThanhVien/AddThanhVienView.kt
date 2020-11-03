package luongvany.k12tt.view.thanhVienHDQT.addThanhVien

import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.ThanhVienHDQTController
import luongvany.k12tt.model.datamodel.ThanhVienHDQTEntryModel
import luongvany.k12tt.util.toThanhVienHDQTEntry
import luongvany.k12tt.view.thanhVienHDQT.Form
import tornadofx.*

class AddThanhVienView : View("Thêm thành viên"){
    private val model = ThanhVienHDQTEntryModel()
    private val itemController: ThanhVienHDQTController by inject()
    private val mainController: MainController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
        center = formView.root
        bottom = button("Save"){
            enableWhen(model.valid)
            action{
                model.commit{
                    model.hoiDongQuantri.value = mainController.convertToId(formView.hdqt, ", Nhiệm kỳ")
                    itemController.add(model.toThanhVienHDQTEntry())
                    model.rollback()
                }
            }
        }
    }
}