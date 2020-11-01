package luongvany.k12tt.controller

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import luongvany.k12tt.view.departmentview.departmenttableview.RightView
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import tornadofx.*
import java.lang.Exception

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
    private val idAndName = execute {
        DepartmentEntryTbl.slice(DepartmentEntryTbl.departmentName, DepartmentEntryTbl.departmentId).selectAll().map {
            toFormString(it[DepartmentEntryTbl.departmentId], it[DepartmentEntryTbl.departmentName])
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<DepartmentEntryModel> by singleAssign()

    init {
        listName = idAndName
        items = listOfItems
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
        idAndName.add(idAndName.size-1, toFormString(departmentEntry.id, departmentEntry.departmentName))
    }

    fun edit(content: DepartmentEntry, indexItem: DepartmentEntryModel){
        enableConsoleLogger()
        try {
            execute {
                DepartmentEntryTbl.update({
                    DepartmentEntryTbl.departmentId eq indexItem.id.value
                }) {
                    it[departmentId] = content.id
                    it[departmentName] = content.departmentName
                    it[directorateId] = content.directorateId
                    it[managerId] = content.managertId
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            idAndName.remove(toFormString(indexItem.id.value, indexItem.departmentName.value))
            idAndName.add(toFormString(content.id, content.departmentName))

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: DepartmentEntryModel){
        execute {
            DepartmentEntryTbl.deleteWhere {
                DepartmentEntryTbl.departmentId eq (model.id.value.toInt())
            }
        }
        listOfItems.remove(model)
        idAndName.remove(toFormString(model.id.value, model.departmentName.value))
    }

    private fun toFormString(content1: Int, content2: String) = """ID: ${content1}, Tên: $content2"""
}
