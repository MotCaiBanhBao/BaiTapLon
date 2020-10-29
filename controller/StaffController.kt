package luongvany.k12tt.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.model.*
import luongvany.k12tt.model.datamodel.StaffEntry
import luongvany.k12tt.model.datamodel.StaffEntryModel
import luongvany.k12tt.model.datamodel.StaffEntryTbl
import luongvany.k12tt.model.datamodel.toStaffEntry
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import luongvany.k12tt.view.staffview.stafftableview.RightView
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import tornadofx.*

class StaffController: Controller(){

    private val rightView: RightView by inject()
    val gioiTinh = FXCollections.observableArrayList<Sex>(Sex.NAM, Sex.NU, Sex.GIOITINHTHUBA)
    var listOfItems: ObservableList<StaffEntryModel> = execute {
        StaffEntryTbl.selectAll().map {
            StaffEntryModel().apply {
                item = it.toStaffEntry()
            }
        }.asObservable()
    }

    var items: ObservableList<StaffEntryModel> by singleAssign()

    init {
        items = listOfItems
    }

    fun add(addItem: StaffEntry): StaffEntry {

        val newEntry = execute{
            StaffEntryTbl.insert {
                it[id] = addItem.id
                it[name] = addItem.name
                it[sex] = addItem.sex.toString()
                it[homeTown] = addItem.homeTown
                it[birthDay] = addItem.birthDay.toDate()
                it[departmentId] = addItem.departmentId
                it[salaryId] = addItem.salaryId
                it[img] = addItem.img
            }
        }

        listOfItems.add(
                StaffEntryModel().apply {
                    item = addItem
                }
        )

        return addItem
    }

    fun update(updateItem: StaffEntryModel): Int{
        return execute {
            StaffEntryTbl.update ({
                StaffEntryTbl.id eq(updateItem.id.value.toInt())}){
                it[id] = updateItem.id.value
                it[name] = updateItem.name.value
                it[sex] = updateItem.sex.name
                it[homeTown] = updateItem.homeTown.value
                it[birthDay] = updateItem.birthDay.value.toDate()
                it[departmentId] = updateItem.departmentId.value
                it[salaryId] = updateItem.salaryId.value
                it[img] = updateItem.img.value
            }
        }
    }

    fun delete(model: StaffEntryModel){
        execute {
            StaffEntryTbl.deleteWhere {
                StaffEntryTbl.id eq (model.id.value.toInt())
            }
        }
         listOfItems.remove(model)
    }

    fun changeImg(model: StaffEntryModel){
        rightView.root.clear()
        rightView.root.imageview(model.img){
            fitWidth = 400.0
        }

    }
}