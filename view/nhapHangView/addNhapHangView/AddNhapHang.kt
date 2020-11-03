package luongvany.k12tt.view.nhapHangView.addNhapHangView

import luongvany.k12tt.controller.LuongController
import luongvany.k12tt.controller.MainController
import luongvany.k12tt.controller.NhapHangController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.NhapHangEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.util.toNhapHangEntry
import luongvany.k12tt.util.toStaffEntry
import luongvany.k12tt.view.staffView.Form
import tornadofx.*

class AddNhapHang: View("Thêm nhập hàng"){
    private val model = NhapHangEntryModel()
    private val itemController: NhapHangController by inject()
    private val mainController: MainController by inject()
    private val formView = luongvany.k12tt.view.nhapHangView.Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        model.maNhanVien.value = mainController.convertToId(formView.staff, ", Tên: ")
                        model.maDoiTac.value = mainController.convertToId(formView.doiTac, ", Tên: ")
                        model.maHangHoa.value = mainController.convertToId(formView.hangHoa, ", Tên: ")
                        model.maHoaDon.value = mainController.convertToId(formView.hoaDon, ", Ngày Lập: ")
                        itemController.add(model.toNhapHangEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
