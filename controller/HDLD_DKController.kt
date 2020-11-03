package luongvany.k12tt.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.HDLD_DKEntry
import luongvany.k12tt.model.datamodel.HDLD_DKEntryModel
import luongvany.k12tt.model.datamodel.HDLD_DKEntryTbl
import luongvany.k12tt.model.datamodel.toHDLD_DKEntry
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import tornadofx.*
import java.lang.Exception

class HDLD_DKController : Controller(){
    var listOfItems: ObservableList<HDLD_DKEntryModel> = execute {
        HDLD_DKEntryTbl.selectAll().map {
            HDLD_DKEntryModel().apply {
                item = it.toHDLD_DKEntry()
            }
        }.asObservable()
    }

    var listName: ObservableList<String> by singleAssign()
    var items: ObservableList<HDLD_DKEntryModel> by singleAssign()

    init {
        items = listOfItems
    }

    fun add(addItem: HDLD_DKEntry){
        try {
            execute{
                HDLD_DKEntryTbl.insert {
                    it[maHopDong] = addItem.maHDLD
                    it[maDieuKhoan] = addItem.maDieuKhoan
                }
            }

            listOfItems.add(
                HDLD_DKEntryModel().apply {
                    item = addItem
                }
            )
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: HDLD_DKEntryModel){
        execute {
            HDLD_DKEntryTbl.deleteWhere {
                HDLD_DKEntryTbl.maDieuKhoan eq (model.maDieuKhoan.value.toInt()) and
                        (HDLD_DKEntryTbl.maHopDong eq (model.maHDLD.value.toInt()))
            }
        }
        listOfItems.remove(model)
    }
}