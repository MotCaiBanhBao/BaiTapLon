package luongvany.k12tt.view.damNhiemView.damNhiemTableView

import luongvany.k12tt.controller.DamNhiemController
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.DamNhiemEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: DamNhiemController by inject()
    var mTableView: TableViewEditModel<DamNhiemEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Mã chức vụ", DamNhiemEntryModel::maChucVu)
        column("Mã hợp đồng", DamNhiemEntryModel::maHopDong)
        column("Mã nhân viên", DamNhiemEntryModel::maNhanVien)

    }
}
