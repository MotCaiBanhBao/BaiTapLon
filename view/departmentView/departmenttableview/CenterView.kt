package luongvany.k12tt.view.departmentView.departmenttableview

import luongvany.k12tt.controller.DepartmentController
import tornadofx.*
import luongvany.k12tt.model.datamodel.DepartmentEntryModel

class CenterView: View() {
    private val itemController: DepartmentController by inject()
    var selectedDepartment: TableViewEditModel<DepartmentEntryModel> by singleAssign()

    override val root = tableview(itemController.items){

        selectedDepartment = editModel

        column("Id", DepartmentEntryModel::id)
        column("Name", DepartmentEntryModel::departmentName)
        column("Manager Id", DepartmentEntryModel::managerId)
        column("Directorate Id", DepartmentEntryModel::directorateId)

        onSelectionChange {
            selectedDepartment.tableView.selectedItem?.let { it -> itemController.showStaff(it) }
        }

    }



}


