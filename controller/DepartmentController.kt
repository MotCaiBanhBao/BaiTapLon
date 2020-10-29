package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.selectAll
import tornadofx.*

class DepartmentController: Controller(){
    var listOfItems: ObservableList<DepartmentEntryModel> = execute {
        DepartmentEntryTbl.selectAll().map {
            DepartmentEntryModel().apply{
                item = it.toDepartmentEntry()
            }
        }.asObservable()
    }
    val listOfName = execute {
        DepartmentEntryTbl.slice(DepartmentEntryTbl.departmentName).selectAll().map {
            it[DepartmentEntryTbl.departmentName]
        }.asObservable()
    }
    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<DepartmentEntryModel> by singleAssign()

    init {
        listName = listOfName
        items = listOfItems
    }


}
