package luongvany.k12tt.view.dieuKhoanLaoDong.addDKLDView

import luongvany.k12tt.controller.DieuKhoanLaoDongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.model.datamodel.DieuKhoanLaoDongEntryModel
import luongvany.k12tt.util.toDieuKhoanLaoDongEntry
import luongvany.k12tt.view.dieuKhoanLaoDong.Form
import tornadofx.*

class AddDKLD: View("Thêm điều khoản lao động "){
    private val model = DieuKhoanLaoDongEntryModel()
    private val itemController: DieuKhoanLaoDongController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toDieuKhoanLaoDongEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
