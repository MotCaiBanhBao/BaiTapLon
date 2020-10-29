package luongvany.k12tt.model.datamodel

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.sql.Table
import tornadofx.*

object HoiDongQuanTriEntryTbl : Table(){
    val maHoiDongQuanTri = integer("Hội đồng quản trị").primaryKey()
    val nhiemKy = varchar("Nhiệm kỳ", 100)
}

class HoiDongQuanTriEntry(maHoiDongQuanTri: Int, nhiemKy: String){
    val maHoiDongQuanTriProperty = SimpleIntegerProperty(maHoiDongQuanTri)
    var maHoiDongQuanTri by maHoiDongQuanTriProperty

    val nhiemKyProperty = SimpleStringProperty(nhiemKy)
    var nhiemKy by nhiemKyProperty
}

class HoiDongQuanTriEntryModel : ItemViewModel<HoiDongQuanTriEntry>() {
    val maHoiDongQuanTri = bind(HoiDongQuanTriEntry::maHoiDongQuanTriProperty)
    val nhiemKy = bind(HoiDongQuanTriEntry::nhiemKyProperty)
}


