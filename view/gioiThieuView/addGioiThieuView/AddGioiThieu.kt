package luongvany.k12tt.view.gioiThieuView.addGioiThieuView

import luongvany.k12tt.controller.GioiThieuController
import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.model.datamodel.GioiThieuEntryModel
import luongvany.k12tt.util.toGioiThieuEntry
import luongvany.k12tt.view.gioiThieuView.Form
import tornadofx.*

class AddGioiThieu: View("Thêm giới thiệu"){
    private val model = GioiThieuEntryModel()
    private val itemController: GioiThieuController by inject()
    private val mainController: MainController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        model.maHangHoa.value = mainController.convertToId(formView.hangHoa, ", Tên: ")
                        model.maHopDong.value = mainController.convertToId(formView.hopDong, ", Thòi gian thực hiện: ")
                        model.maKhachHang.value = mainController.convertToId(formView.khachHang, ", Tên: ")
                        model.maNhanVien.value = mainController.convertToId(formView.staff, ", Tên: ")
                        itemController.add(model.toGioiThieuEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
