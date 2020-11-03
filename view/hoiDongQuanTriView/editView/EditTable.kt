package luongvany.k12tt.view.hoiDongQuanTriView.editView


import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.util.coppy
import luongvany.k12tt.util.toHoiDongQTEntry
import luongvany.k12tt.view.hoiDongQuanTriView.Form
import luongvany.k12tt.view.hoiDongQuanTriView.hoiDongQuanTriTableView.CenterView
import tornadofx.*

class EditTable: View("Edit table") {
    private val centerView: CenterView by inject()
    private val hdqtController: HDQTController by inject()
    private val selected =  centerView.mTableView.tableView.selectedItem!!.coppy()
    private val index =  centerView.mTableView.tableView.selectedItem!!
    private val formView = Form(selected)

    override val root = borderpane {
        center = formView.root
        bottom = button("Save") {
            action {
                selected.nhiemKy.value = hdqtController.toNhiemKyString(formView.nhiemKyDauProperty.value, formView.nhiemKyCuoiProperty.value)
                hdqtController.edit(content = selected.toHoiDongQTEntry(), indexItem = index)
            }
        }
    }
}
