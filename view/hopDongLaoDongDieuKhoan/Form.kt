package luongvany.k12tt.view.hopDongLaoDongDieuKhoan

import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.controller.DieuKhoanLaoDongController
import luongvany.k12tt.controller.HDLD_DKController
import luongvany.k12tt.controller.HopDongLaoDongController
import luongvany.k12tt.model.datamodel.HDLD_DKEntryModel
import luongvany.k12tt.view.dieuKhoanLaoDong.addDKLDView.AddDKLD
import luongvany.k12tt.view.hDLDView.addHopDongLaoDongView.AddHDLD
import tornadofx.*

class Form(val model: HDLD_DKEntryModel): View("HDLD_DK Form") {
    private val itemController: HDLD_DKController by inject()
    private val hopDongLaoDongController: HopDongLaoDongController by inject()
    var hopDongLaoDong = SimpleStringProperty(hopDongLaoDongController.listName[0])
    private val dieuKhoanLaoDongController: DieuKhoanLaoDongController by inject()
    val dieuKhoanLaoDong = SimpleStringProperty(dieuKhoanLaoDongController.listName[0])
    private val addDK: AddDKLD by inject()
    private val addHDLD: AddHDLD by inject()

    override val root = form {
        field("Mã hợp đồng lao động") {
            combobox(hopDongLaoDong) {
                items = hopDongLaoDongController.listName

                hopDongLaoDong.onChange{
                    if(it == "+Add hợp đồng lao động")
                        addHDLD.openWindow()
                }
            }
        }
        field("Điều khoản lao động") {
            combobox(dieuKhoanLaoDong){

                items = dieuKhoanLaoDongController.listName
                dieuKhoanLaoDong.onChange {
                    if(it == "+Add new điều khoản lao động"){
                        find(addDK::class).openModal(owner = null)
                    }
                }
            }
        }
    }
}
