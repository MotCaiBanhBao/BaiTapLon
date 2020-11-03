package luongvany.k12tt.view.hDLDView.addHopDongLaoDongView

import luongvany.k12tt.controller.HopDongLaoDongController
import luongvany.k12tt.model.datamodel.HopDongLaoDongEntryModel
import luongvany.k12tt.view.hDLDView.Form
import tornadofx.*

class AddHDLD: View("Thêm nhân viên"){
    private val model = HopDongLaoDongEntryModel()
    private val itemController: HopDongLaoDongController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        model.rollback()
                    }
                }
            }
        }
    }
