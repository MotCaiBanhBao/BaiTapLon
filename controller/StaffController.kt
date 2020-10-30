package luongvany.k12tt.controller

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.model.*
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import luongvany.k12tt.view.staffview.stafftableview.RightView
import org.jetbrains.exposed.sql.*
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
    private val listOfName = execute {
        StaffEntryTbl.slice(StaffEntryTbl.name).selectAll().map {
            it[StaffEntryTbl.name]
        }.asObservable()
    }
    private val idAndName = execute {
        StaffEntryTbl.slice(StaffEntryTbl.name, StaffEntryTbl.id).selectAll().map {
            it[StaffEntryTbl.name] to it[StaffEntryTbl.id]
        }.asObservable()
    }

    var pair: ObservableList<Pair<String, Int>> by singleAssign()
    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<StaffEntryModel> by singleAssign()

    init {
        pair = idAndName
        listName = listOfName
        items = listOfItems
    }

    fun find(item: DepartmentEntryModel) = execute {
        StaffEntryTbl.slice(StaffEntryTbl.name).select{
            StaffEntryTbl.departmentId eq item.id.value
        }.map{
            it[StaffEntryTbl.name]
        }.asObservable()
    }
    fun add(addItem: StaffEntry): StaffEntry {

        execute{
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

    fun convertToId(name: SimpleStringProperty): Int {
        for(onePair in pair){
            if(name.value == onePair.first)
                return onePair.second
        }
        return 1
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

    fun edit(content: StaffEntry, indexItem: StaffEntryModel){
        enableConsoleLogger()
        execute{
            StaffEntryTbl.update({
                StaffEntryTbl.id eq indexItem.id.value
            }){
                it[id] = content.id
                it[name] = content.name
                it[homeTown] = content.homeTown
                it[sex] = content.sex.toString()
                it[birthDay] = content.birthDay.toDate()
                it[departmentId] = content.departmentId
                it[salaryId] = content.salaryId
                it[img] = content.img
            }
        }
        listOfItems.find {
            it == indexItem
        }?.let{
            apply {
                it.id.value = content.id
                it.name.value = content.name
                it.homeTown.value = content.homeTown
                it.birthDay.value = content.birthDay
                it.img.value = content.img
                it.sex.value = content.sex
                it.salaryId.value = content.salaryId
                it.departmentId.value = content.departmentId
            }
        }
    }
}