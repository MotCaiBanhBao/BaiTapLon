package luongvany.k12tt.view.doiTacView.addDoiTacView

import luongvany.k12tt.controller.DoiTacController
import luongvany.k12tt.model.datamodel.DoiTacEntryModel
import luongvany.k12tt.util.toDoiTacEntry
import luongvany.k12tt.view.doiTacView.Form
import tornadofx.*

class AddDoiTac: View("Thêm đối tác"){
    private val model = DoiTacEntryModel()
    private val itemController: DoiTacController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toDoiTacEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
