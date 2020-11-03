package luongvany.k12tt.view.damNhiemView.addDamNhiemView

import luongvany.k12tt.controller.DamNhiemController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.model.datamodel.DamNhiemEntryModel
import luongvany.k12tt.util.toDamNhiemEntry
import luongvany.k12tt.view.damNhiemView.Form
import tornadofx.*

class AddDamNhiem: View("Thêm nhân viên"){
    private val model = DamNhiemEntryModel()
    private val itemController: DamNhiemController by inject()
    private val mainController: MainController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        model.maChucVu.value = mainController.convertToId(formView.chucVu, ", Tên: ")
                        model.maHopDong.value = mainController.convertToId(formView.hdld, ", Ngày thành lập: ")
                        model.maNhanVien.value = mainController.convertToId(formView.nhanVien, ", Tên: ")
                        itemController.add(model.toDamNhiemEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
