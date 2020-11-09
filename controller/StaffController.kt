package luongvany.k12tt.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.model.*
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import luongvany.k12tt.view.staffView.staffTableView.RightView
import org.jetbrains.exposed.sql.*
import tornadofx.*
import java.lang.Exception

class StaffController: Controller(){

    private val rightView: RightView by inject()
    val gioiTinh = FXCollections.observableArrayList(Sex.NAM, Sex.NU, Sex.GIOITINHTHUBA)
    var listOfItems: ObservableList<StaffEntryModel> = execute {
        StaffEntryTbl.selectAll().map {
            StaffEntryModel().apply {
                item = it.toStaffEntry()
            }
        }.asObservable()
    }

    private val idAndName = execute {
        StaffEntryTbl.slice(StaffEntryTbl.name, StaffEntryTbl.id).selectAll().map {
            toFormString(it[StaffEntryTbl.id], it[StaffEntryTbl.name])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<StaffEntryModel> by singleAssign()

    init {
        idAndName.add(" ")
        idAndName.add("+Add Nhân Viên")
        listName = idAndName
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
        try {
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
            idAndName.add(if(idAndName.size==0) 0 else idAndName.size-1 ,toFormString(addItem.id, addItem.name))
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }

        return addItem
    }

    fun delete(model: StaffEntryModel){
        execute {
            StaffEntryTbl.deleteWhere {
                StaffEntryTbl.id eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndName.remove(toFormString(model.id.value, model.name.value))
    }

    fun changeImg(model: StaffEntryModel){
        rightView.root.clear()
        rightView.root.imageview(model.img){
            fitWidth = 400.0
        }
    }

    fun edit(content: StaffEntry, indexItem: StaffEntryModel){
        enableConsoleLogger()
        try {
            execute {
                StaffEntryTbl.update({
                    StaffEntryTbl.id eq indexItem.id.value
                }) {
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
            }?.let {
                it.item = content
            }

            idAndName.remove(toFormString(indexItem.id.value, indexItem.name.value))
            idAndName.add(toFormString(content.id, content.name))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    private fun toFormString(content1: Int, content2: String) = """ID: ${content1}, Tên: $content2"""
}