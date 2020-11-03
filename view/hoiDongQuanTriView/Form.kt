package luongvany.k12tt.view.hoiDongQuanTriView

import javafx.beans.property.SimpleObjectProperty
import luongvany.k12tt.controller.HDQTController
import luongvany.k12tt.model.datamodel.HoiDongQuanTriEntry
import luongvany.k12tt.model.datamodel.HoiDongQuanTriEntryModel
import tornadofx.*
import java.time.LocalDate

class Form(val model: HoiDongQuanTriEntryModel): View("Hội đồng quản trị form") {
    private val itemController: HDQTController by inject()
    lateinit var nhiemKy: Pair<LocalDate, LocalDate>
    var nhiemKyDauProperty: SimpleObjectProperty<LocalDate>
    var nhiemKyCuoiProperty: SimpleObjectProperty<LocalDate>

    init {
        if (model.nhiemKy.value == null){
            nhiemKyDauProperty = SimpleObjectProperty(LocalDate.now())
            nhiemKyCuoiProperty = SimpleObjectProperty(LocalDate.now())
            model.nhiemKy.value = itemController.toNhiemKyString(nhiemKyDauProperty.value, nhiemKyCuoiProperty.value)
        }
        else{
            nhiemKy = itemController.toLocalDate(model.nhiemKy.value)
            nhiemKyDauProperty = SimpleObjectProperty(nhiemKy.first)
            nhiemKyCuoiProperty = SimpleObjectProperty(nhiemKy.second)
        }
    }


    override val root = form {
        fieldset {
            field("Mã hội đồng quản trị") {
                textfield(model.maHoiDongQuanTri) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            itemController.items.any { itemId -> itemId.maHoiDongQuanTri.value == it.toInt() } -> error("Id đã trùng")
                            !it.isLong() -> error("Phải là số")
                            else -> null
                        }
                    }
                }
            }
            field("Nhiệm kỳ") {
                text("Từ")
                datepicker(nhiemKyDauProperty)
                text("Đến")
                datepicker(nhiemKyCuoiProperty)
            }

        }
    }
}