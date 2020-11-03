package luongvany.k12tt.view.hoiDongQuanTriView.addHoiDongQT

import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.model.datamodel.HoiDongQuanTriEntryModel
import luongvany.k12tt.util.toHoiDongQTEntry
import luongvany.k12tt.view.hoiDongQuanTriView.Form
import tornadofx.*

class ThemHDQT : View(){

    private val model: HoiDongQuanTriEntryModel by inject()
    private val itemController: HDQTController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
        center = formView.root
        bottom = button("Save"){
            enableWhen(model.valid)
            action{
                model.commit{
                    model.nhiemKy.value = itemController.toNhiemKyString(formView.nhiemKyDauProperty.value, formView.nhiemKyCuoiProperty.value)
                    itemController.add(model.toHoiDongQTEntry())
                    model.rollback()
                }
            }
        }
    }
}

