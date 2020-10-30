package luongvany.k12tt.controller

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.execute
import luongvany.k12tt.view.departmentview.departmenttableview.RightView
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import tornadofx.*

class DepartmentController: Controller(){
    private val rightView: RightView by inject()
    private val staffController: StaffController by inject()
    var listOfItems: ObservableList<DepartmentEntryModel> = execute {
        DepartmentEntryTbl.selectAll().map {
            DepartmentEntryModel().apply{
                item = it.toDepartmentEntry()
            }
        }.asObservable()
    }
    private val listOfName = execute {
        DepartmentEntryTbl.slice(DepartmentEntryTbl.departmentName).selectAll().map {
            it[DepartmentEntryTbl.departmentName]
        }.asObservable()
    }
    private val idAndName = execute {
        DepartmentEntryTbl.slice(DepartmentEntryTbl.departmentName, DepartmentEntryTbl.departmentId).selectAll().map {
            it[DepartmentEntryTbl.departmentName] to it[DepartmentEntryTbl.departmentId]
        }.asObservable()
    }

    var pair: ObservableList<Pair<String, Int>> by singleAssign()
    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<DepartmentEntryModel> by singleAssign()

    init {
        pair = idAndName
        listName = listOfName
        items = listOfItems
    }


    fun convertToId(name: SimpleStringProperty): Int {
        for(onePair in pair){
            if(name.value == onePair.first)
                return onePair.second
        }
        return 1
    }

    fun showStaff(department: DepartmentEntryModel){
        rightView.root.clear()
        rightView.root.borderpane() {
            top = label("Danh sách nhân viên"){
                style{
                    fontWeight = FontWeight.BOLD
                }
            }
            center = listview<String> {
                items = staffController.find(department)
            }
        }
    }

    fun add(departmentEntry: DepartmentEntry){
        execute {
            DepartmentEntryTbl.insert {
                it[departmentId] = departmentEntry.id
                it[departmentName] = departmentEntry.departmentName
                it[managerId] = departmentEntry.managertId
                it[directorateId] = departmentEntry.directorateId
            }
        }

        listOfItems.add(
                DepartmentEntryModel().apply {
                    item = departmentEntry
                }
        )
    }


}
