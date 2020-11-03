package luongvany.k12tt.view.thanhVienHDQT.thanhVienHDQT

import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.controller.ThanhVienHDQTController
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.model.datamodel.ThanhVienHDQTEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View(){
    private val itemController: ThanhVienHDQTController by inject()
    var mTableView: TableViewEditModel<ThanhVienHDQTEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Id", ThanhVienHDQTEntryModel::id)
        column("Name", ThanhVienHDQTEntryModel::name)
        column("So Dien Thoai", ThanhVienHDQTEntryModel::soDienThoai)
        column("So Chung Minh", ThanhVienHDQTEntryModel::soChungMinh)
        column("Hoi Dong Quantri", ThanhVienHDQTEntryModel::hoiDongQuantri)
    }
}