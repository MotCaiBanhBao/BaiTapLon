package luongvany.k12tt.view.staffView.staffTableView

import javafx.geometry.Pos
import javafx.scene.shape.Circle
import javafx.scene.text.TextAlignment
import luongvany.k12tt.controller.StaffController
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryTbl.birthDay
import luongvany.k12tt.model.datamodel.StaffEntryTbl.homeTown
import luongvany.k12tt.model.datamodel.StaffEntryTbl.name
import luongvany.k12tt.model.datamodel.StaffEntryTbl.sex
import luongvany.k12tt.util.createDatabase
import luongvany.k12tt.util.showThumbnail
import tornadofx.*
import javax.swing.GroupLayout
import javax.swing.text.StyleConstants.Alignment

class CenterView : View("My View") {
    private val itemController: StaffController by inject()

    var mTableView: TableViewEditModel<StaffEntryModel> by singleAssign()
    override val root = tableview(itemController.items){
        mTableView = editModel

        column("Name", StaffEntryModel::name){
            prefWidth = 200.0
            cellFormat{
                with(tableRow.item as StaffEntryModel){
                    graphic = showThumbnail()
                    text = name.value
                }
            }
        }
        column("Home Town", StaffEntryModel::homeTown){
            cellFormat {
                alignment = Pos.CENTER_LEFT
                text = this.item.toString()
            }
        }
        column("Sex", StaffEntryModel::sex){
            cellFormat {
                alignment = Pos.CENTER_LEFT
                text = this.item.toString()
            }
        }
        column("Birthday", StaffEntryModel::birthDay){
            cellFormat {

                alignment = Pos.CENTER_LEFT
                text = this.item.toString()
            }
        }
        onSelectionChange {
            mTableView.tableView.selectedItem?.let { it1 -> itemController.changeImg(it1) }
        }

    }
}
