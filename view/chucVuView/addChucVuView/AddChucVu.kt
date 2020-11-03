package luongvany.k12tt.view.chucVuView.addChucVuView

import luongvany.k12tt.controller.ChucVuController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.model.datamodel.ChucVuEntryModel
import luongvany.k12tt.util.toChucVuEntry
import tornadofx.*

class AddChucVu: View("Thêm nhân viên"){
    private val model = ChucVuEntryModel()
    private val itemController: ChucVuController by inject()
    private val mainController: MainController by inject()
    private val formView = luongvany.k12tt.view.chucVuView.Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toChucVuEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
