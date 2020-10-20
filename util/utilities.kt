package luongvany.k12tt.util

import luongvany.k12tt.model.StaffEntry
import luongvany.k12tt.model.StaffEntryModel
import org.joda.time.DateTime
import java.time.LocalDate
import luongvany.k12tt.model.Sex

fun DateTime.toJavaLocalDate(): LocalDate{

    return LocalDate.of(this.year, this.monthOfYear, this.dayOfMonth)

}

fun LocalDate.toDate(default: DateTime = DateTime(1900, 1, 1, 0, 0, 0)): DateTime{
    return DateTime(this.year, this.monthValue, this.dayOfMonth, 0, 0)
}

fun StaffEntryModel.toStaffEntry(): StaffEntry{
    return StaffEntry(this.id.value, this.name.value, this.homeTown.value, this.sex.value, this.birthDay.value, this.departmentId.value,
            this.salaryId.value, this.img.value)
}

fun String.convertToSex(): Sex{
    return if(this.toLowerCase().contains("nam"))
        Sex.NAM
    else if(this.toLowerCase().contains("nu"))
        Sex.NU
    else
        Sex.GIOITINHTHUBA
}