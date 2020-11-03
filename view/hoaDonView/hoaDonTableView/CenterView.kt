package luongvany.k12tt.view.hoaDonView.hoaDonTableView

import luongvany.k12tt.controller.HoaDonController
import luongvany.k12tt.model.datamodel.HoaDonEntryModel
import tornadofx.*
import tornadofx.column

class CenterView : View("My View") {
    private val itemController: HoaDonController by inject()
    var mTableView: TableViewEditModel<HoaDonEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel
        column("Id", HoaDonEntryModel::id)
        column("So Tien", HoaDonEntryModel::soTien)
        column("Ngay Lap", HoaDonEntryModel::ngayLap)


    }
}
