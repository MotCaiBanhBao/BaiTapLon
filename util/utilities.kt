package luongvany.k12tt.util

import org.joda.time.DateTime
import java.time.LocalDate

fun DateTime.toJavaLocalDate(): LocalDate{

    return LocalDate.of(this.year, this.monthOfYear, this.dayOfMonth)
}