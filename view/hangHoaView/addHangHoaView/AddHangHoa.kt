package luongvany.k12tt.view.hangHoaView.addHangHoaView

import luongvany.k12tt.controller.HangHoaController
import luongvany.k12tt.model.datamodel.HangHoaEntryModel
import luongvany.k12tt.util.toHangHoaEntry
import luongvany.k12tt.view.hangHoaView.Form
import tornadofx.*

class AddHangHoa: View("Thêm hàng hóa"){
    private val model = HangHoaEntryModel()
    private val itemController: HangHoaController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toHangHoaEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
