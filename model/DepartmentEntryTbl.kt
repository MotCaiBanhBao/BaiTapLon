package luongvany.k12tt.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.util.convertToSex
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*
import java.time.LocalDate
import java.time.Period


fun ResultRow.toDepartmentEntry() = DepartmentEntry(
        this[DepartmentEntry.id],
        this[DepartmentEntry.name],
        this[DepartmentEntry.homeTown],
        this[DepartmentEntry.sex].convertToSex(),
        this[DepartmentEntry.birthDay].toJavaLocalDate(),
        this[DepartmentEntry.departmentId],
        this[DepartmentEntry.salaryId],
        this[DepartmentEntry.img]
)

object DepartmentEntryTbl: Table(){
    val departmentID = integer("id").autoIncrement().primaryKey()
    val departmentName = varchar("name", 30)
    val managerID = (integer("manager id") references StaffEntryTbl.id)
    val sex = varchar("sex", 30)
    val birthDay = date("date")
    val departmentId = integer("departmentID")
    val salaryId = integer("salaryId")
    val img = varchar("imagine", 100)
}

class DepartmentEntry(id: Int, name: String, homeTown: String, sex: Sex,
                 birthDay: LocalDate, departmentId: Int, salaryId: Int, imgUrl: String){

    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

    val homeTownProperty = SimpleStringProperty(homeTown)
    var homeTown by homeTownProperty

    val sexProperty = SimpleObjectProperty<Sex>(sex)
    var sex by sexProperty

    val birthDayProperty = SimpleObjectProperty<LocalDate>(birthDay)
    var birthDay by birthDayProperty

    val departmentIdProperty = SimpleIntegerProperty(departmentId)
    var departmentId by departmentIdProperty

    val salaryIdProperty = SimpleIntegerProperty(salaryId)
    var salaryId by salaryIdProperty

    val imgProperty = SimpleStringProperty(imgUrl)
    var img by imgProperty
}

class DepartmentEntryModel: ItemViewModel<StaffEntry>(){
    val id = bind{item?.idProperty}
    val name = bind{item?.nameProperty}
    val homeTown = bind{item?.homeTownProperty}
    val sex = bind{item?.sexProperty}
    val birthDay = bind{item?.birthDayProperty}
    val departmentId = bind{item?.departmentIdProperty}
    val salaryId = bind{item?.salaryIdProperty}
    val img = bind{item?.imgProperty}

    init{
        img.value = "person-male.png"
    }
}