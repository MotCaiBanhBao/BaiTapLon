package luongvany.k12tt.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.image.Image
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*
import java.time.LocalDate
import java.time.Period


fun ResultRow.toStaffEntry() = StaffEntry(
        this[StaffEntryTbl.id],
        this[StaffEntryTbl.name],
        this[StaffEntryTbl.homeTown],
        Sex.valueOf(this[StaffEntryTbl.sex]),
        this[StaffEntryTbl.birthDay].toJavaLocalDate(),
        this[StaffEntryTbl.departmentId],
        this[StaffEntryTbl.salaryId],
        Image(this[StaffEntryTbl.img])
)

object StaffEntryTbl: Table(){
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 30)
    val homeTown = varchar("homeTown", 30)
    val sex = varchar("sex", 30)
    val birthDay = date("date")
    val departmentId = integer("departmentID")
    val salaryId = integer("salaryId")
    val img = varchar("imagine", 30)

}

class StaffEntry(id: Int, name: String, homeTown: String, sex: Sex,
            birthDay: LocalDate, departmentId: Int, salaryId: Int, img: Image){

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

    val imgProperty = SimpleObjectProperty<Image>(img)
    var img by imgProperty

    val age: String get() = if (Period.between(birthDay, LocalDate.now()).years == 0) {
        " " + Period.between(birthDay, LocalDate.now()).months + " tháng"
    } else{
        " " + Period.between(birthDay, LocalDate.now()).years + " Tuổi"
    }
}

class StaffEntryModel: ItemViewModel<StaffEntry>(){
    val id = bind{item?.idProperty}
    val name = bind{item?.nameProperty}
    val homeTown = bind{item?.homeTownProperty}
    val sex = bind{item?.sexProperty}
    val birthDay = bind{item?.birthDayProperty}
    val departmentId = bind{item?.departmentIdProperty}
    val salaryId = bind{item?.salaryIdProperty}
    val img = bind{item?.imgProperty}
}