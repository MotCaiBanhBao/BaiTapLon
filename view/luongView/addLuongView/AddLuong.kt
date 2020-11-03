package luongvany.k12tt.view.luongView.addLuongView

import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.model.datamodel.LuongEntryModel
import luongvany.k12tt.util.toLuongEntry
import tornadofx.*

class AddLuong: View("Thêm nhân viên"){
    private val model = LuongEntryModel()
    private val itemController: LuongController by inject()
    private val formView = luongvany.k12tt.view.luongView.Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toLuongEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
