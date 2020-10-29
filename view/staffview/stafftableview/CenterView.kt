package luongvany.k12tt.view.staffview.stafftableview

import luongvany.k12tt.controller.ItemController
import luongvany.k12tt.model.datamodel.StaffEntryModel
import tornadofx.*

class CenterView : View("My View") {
    val itemController: ItemController by inject()
    var mTableView: TableViewEditModel<StaffEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        mTableView = editModel

        column("Id", StaffEntryModel::id)
        column("Name", StaffEntryModel::name)
        column("Home Town", StaffEntryModel::homeTown)
        column("Sex", StaffEntryModel::sex)
        column("Birthday", StaffEntryModel::birthDay)
        column("Department", StaffEntryModel::departmentId)
        column("Salary id", StaffEntryModel::salaryId)
        onSelectionChange {
            mTableView.tableView.selectedItem?.let { it1 -> itemController.changeImg(it1) }
        }
    }
}
