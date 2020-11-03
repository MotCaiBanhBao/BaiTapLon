package luongvany.k12tt.view.phuCapView.addPhuCapView

import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.PhuCapController
import luongvany.k12tt.model.datamodel.PhuCapEntryModel
import luongvany.k12tt.util.toPhuCapEntry
import tornadofx.*

class AddPhuCap: View("Thêm phụ cấp"){
    private val model = PhuCapEntryModel()
    private val itemController: PhuCapController by inject()
    private val mainController: MainController by inject()
    private val formView = luongvany.k12tt.view.phuCapView.Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                       model.maThanhVien.value = mainController.convertToId(formView.name, ", Tên: ")
                        itemController.add(model.toPhuCapEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
