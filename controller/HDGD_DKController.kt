package luongvany.k12tt.controller

import javafx.collections.ObservableList
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.execute
import luongvany.k12tt.util.toDate
import org.jetbrains.exposed.sql.*
import tornadofx.*
import java.lang.Exception

class HDGD_DKController : Controller(){

    var listOfItems: ObservableList<HDGD_DKEntryModel> = execute {
        HDGD_DKEntryTbl.selectAll().map {
            HDGD_DKEntryModel().apply {
                item = it.toHDGD_DKEntry()
            }
        }.asObservable()
    }

    var items: ObservableList<HDGD_DKEntryModel> by singleAssign()

    init {
        items = listOfItems
    }

    fun add(addItem: HDGD_DKEntry){
        try {
            execute{
                HDGD_DKEntryTbl.insert {
                    it[maDieuKhoan] = addItem.maDieuKhoan
                    it[maHopDong] = addItem.maHopDong
                }
            }

            listOfItems.add(
                HDGD_DKEntryModel().apply {
                    item = addItem
                }
            )
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }

    fun delete(model: HDGD_DKEntryModel){
        execute {
            HDGD_DKEntryTbl.deleteWhere {
                HDGD_DKEntryTbl.maDieuKhoan eq (model.maDieuKhoan.value.toInt()) and
                        (HDGD_DKEntryTbl.maHopDong eq (model.maHopDong.value.toInt()))
            }
        }
        listOfItems.remove(model)
    }

    fun edit(content: HDGD_DKEntry, indexItem: HDGD_DKEntryModel){
        enableConsoleLogger()
        try {
            execute {
                HDGD_DKEntryTbl.update({
                    HDGD_DKEntryTbl.maDieuKhoan eq (indexItem.maDieuKhoan.value.toInt()) and
                            (HDGD_DKEntryTbl.maHopDong eq (indexItem.maHopDong.value.toInt()))
                }) {
                    it[maDieuKhoan] = content.maDieuKhoan
                    it[maHopDong] = content.maHopDong
                }
            }

            listOfItems.find {
                it == indexItem
            }?.let {
                it.item = content
            }

            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }
}