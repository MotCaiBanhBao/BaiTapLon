package luongvany.k12tt.controller

import javafx.collections.ObservableList
import javafx.scene.image.Image
import luongvany.k12tt.model.*
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import tornadofx.*
import java.time.LocalDate

class ItemController: Controller(){



    private val listOfItems: ObservableList<StaffEntryModel> = execute {
        StaffEntryTbl.selectAll().map {
            StaffEntryModel().apply {
                item = it.toStaffEntry()
            }
        }.asObservable()
    }

    var staffModel = StaffEntryModel()

    init {
        execute {
            StaffEntryTbl.deleteWhere {
                StaffEntryTbl.id eq 1
            }
        }
        add(1, "Lương Văn Ý", "NAM", "Bình Định", LocalDate.of(2000, 10, 26), 1, 1, "person-male.png")
        listOfItems.forEach{
            println(it.id.value)
        }
    }

    fun add(newId: Int, newName: String, newSex: String, newHomeTown: String, newBirthday: LocalDate,
            newDepartmentId: Int, newSalaryId: Int, newImgUrl: String): StaffEntry {

        val newEntry = execute{
            StaffEntryTbl.insert {
                it[id] = newId
                it[name] = newName
                it[sex] = newSex
                it[homeTown] = newHomeTown
                it[birthDay] = newBirthday.toDate()
                it[departmentId] = newDepartmentId
                it[salaryId] = newSalaryId
                it[img] = newImgUrl
            }
        }

        return StaffEntry(newEntry[StaffEntryTbl.id], newName,newHomeTown,  Sex.valueOf(newSex),
                newBirthday, newDepartmentId, newSalaryId, Image(newImgUrl))
    }

    fun update(updateItem: StaffEntryModel): Int{
        return execute {
            StaffEntryTbl.update ({
                StaffEntryTbl.id eq(updateItem.id.value.toInt())}){
                it[id] = updateItem.id.value
                it[name] = updateItem.name.value
                it[sex] = updateItem.sex.value.display()
                it[homeTown] = updateItem.homeTown.value
                it[birthDay] = updateItem.birthDay.value.toDate()
                it[departmentId] = updateItem.departmentId.value
                it[salaryId] = updateItem.salaryId.value
                it[img] = updateItem.img.value.url
            }
        }
    }

    fun delete(model: StaffEntryModel){
        execute {
            StaffEntryTbl.deleteWhere {
                StaffEntryTbl.id eq (model.id.value.toInt())
            }
        }
    }
}