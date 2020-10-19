package luongvany.k12tt.controller

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.image.Image
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.Staff
import luongvany.k12tt.view.staffview.edittableview.EditTable
import luongvany.k12tt.view.staffview.stafftableview.CenterView
import luongvany.k12tt.view.staffview.stafftableview.RightView
import tornadofx.*
import java.time.LocalDate

class StaffViewController: Controller(){
    var index: Int = 0
    val editTable: EditTable by inject()
    private val rightView: RightView by inject()
    private val leftView: CenterView by inject()

    val gioiTinh = FXCollections.observableArrayList<Sex>(Sex.NAM, Sex.NU, Sex.GIOITINHTHUBA)
    val name = SimpleStringProperty("")
    val idStaff = SimpleStringProperty("")
    val homeTown = SimpleStringProperty("")
    val sex = SimpleObjectProperty<Sex>(Sex.NAM)
    val birthDay = SimpleObjectProperty<LocalDate>(LocalDate.now())
    val departmentId = SimpleStringProperty("")
    val salaryId = SimpleStringProperty("")
    val img = SimpleStringProperty("person-male.png")

    val staffDatas = FXCollections.observableArrayList<Staff>(
            Staff(
                    "1",
                    "Lương Văn Ý",
                    "Bình Định",
                    Sex.NAM,
                    LocalDate.parse("2000-10-26" ),
                    "1", "1",
                    Image("person-male.png")
            ),

            Staff(
                    "2",
                    "Alo",
                    "Bình Định",
                    Sex.NU,
                    LocalDate.parse("1000-11-26" ),
                    "1",
                    "1",
                    Image("person-female.png"))
    )

    fun saveStaff(){
        val staff = Staff(idStaff.value, name.value, homeTown.value,
                sex.value, birthDay.value, departmentId.value,
                salaryId.value, Image(img.value))
        staffDatas.add(staff)
    }
    fun findIndex(): Int?{
        return leftView.root.selectedCell?.row
    }
    fun changeImg(index: Int?){
        rightView.root.clear()
        try {
            rightView.root.imageview {
                fitWidth = 400.0
            }.imageProperty().set(staffDatas[index?:0].img)
        }catch (e: Exception){
            return
        }
    }
    fun deleteRow(index: Int?){
        index?.let{
            staffDatas.removeAt(it)
        }
    }
}

class thongBao: Fragment(){
    override val root = label("Hãy chọn vào nhân viên cần sửa trước khi nhấn edit")
}