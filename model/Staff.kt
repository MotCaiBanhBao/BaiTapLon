package luongvany.k12tt.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import java.time.*
import javafx.scene.image.Image
import tornadofx.*

class Staff(id: String, name: String, homeTown: String, sex: Sex,
            birthDay: LocalDate, departmentId: String, salaryId: String, img: Image){

    val idProperty = SimpleStringProperty(id)
    var id by idProperty

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

    val homeTownProperty = SimpleStringProperty(homeTown)
    var homeTown by homeTownProperty

    val sexProperty = SimpleObjectProperty<Sex>(sex)
    var sex by sexProperty

    val birthDayProperty = SimpleObjectProperty<LocalDate>(birthDay)
    var birthDay by birthDayProperty

    val departmentIdProperty = SimpleStringProperty(departmentId)
    var departmentId by departmentIdProperty

    val salaryIdProperty = SimpleStringProperty(salaryId)
    var salaryId by salaryIdProperty

    val imgProperty = SimpleObjectProperty<Image>(img)
    var img by imgProperty

    val age: String get() = if (Period.between(birthDay, LocalDate.now()).years == 0) {
        " " + Period.between(birthDay, LocalDate.now()).months + " tháng"
    } else{
        " " + Period.between(birthDay, LocalDate.now()).years + " Tuổi"
    }
}
