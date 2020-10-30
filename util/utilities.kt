package luongvany.k12tt.util

import luongvany.k12tt.model.datamodel.StaffEntry
import luongvany.k12tt.model.datamodel.StaffEntryModel
import org.joda.time.DateTime
import java.time.LocalDate
import luongvany.k12tt.model.Sex
import luongvany.k12tt.model.datamodel.DepartmentEntry
import luongvany.k12tt.model.datamodel.DepartmentEntryModel

fun DateTime.toJavaLocalDate(): LocalDate{

    return LocalDate.of(this.year, this.monthOfYear, this.dayOfMonth)

}

fun LocalDate.toDate(default: DateTime = DateTime(1900, 1, 1, 0, 0, 0)): DateTime{
    return DateTime(this.year, this.monthValue, this.dayOfMonth, 0, 0)
}

fun StaffEntryModel.toStaffEntry() = StaffEntry(this.id.value, this.name.value, this.homeTown.value,
        this.sex.value, this.birthDay.value, this.departmentId.value,
        this.salaryId.value, this.img.value)

fun String.convertToSex(): Sex{
    return if(this.toLowerCase().contains("nam"))
        Sex.NAM
    else if(this.toLowerCase().contains("nu"))
        Sex.NU
    else
        Sex.GIOITINHTHUBA
}

fun StaffEntryModel.coppy() = StaffEntryModel().apply {
    item = this@coppy.toStaffEntry()
}
fun StaffEntry.toStaffEntryModel(): StaffEntryModel{
    return StaffEntryModel().apply {
        item = this@toStaffEntryModel
    }
}
fun DepartmentEntryModel.toDepartmentEntry() = DepartmentEntry(this.id.value, this.departmentName.value, this.managerId.value ,this.directorateId.value)