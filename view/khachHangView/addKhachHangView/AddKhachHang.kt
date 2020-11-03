package luongvany.k12tt.view.khachHangView.addKhachHangView

import luongvany.k12tt.controller.KhachHangController
import luongvany.k12tt.model.datamodel.KhachHangEntryModel
import luongvany.k12tt.util.toKhachHangEntry
import luongvany.k12tt.view.khachHangView.Form
import tornadofx.*

class AddKhachHang: View("Thêm nhân viên"){
    private val model = KhachHangEntryModel()
    private val itemController: KhachHangController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toKhachHangEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
