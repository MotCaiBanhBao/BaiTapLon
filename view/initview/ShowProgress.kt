package luongvany.k12tt.view.initview

import javafx.application.Platform
import luongvany.k12tt.app.ApplicationWorkspace
import luongvany.k12tt.controller.LoginController
import luongvany.k12tt.model.datamodel.*
import luongvany.k12tt.util.enableConsoleLogger
import luongvany.k12tt.util.newTransaction
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import tornadofx.*
import kotlin.concurrent.thread

class ShowProgress : View("Init application") {
    private val controller: LoginController by inject()
    override val root = vbox {
        enableConsoleLogger()

        progressbar() {
            thread {
                with(newTransaction()) {
                    exec("create database test;")
                    exec("use test;")

                    SchemaUtils.create(StaffEntryTbl, DepartmentEntryTbl, ChucVuEntryTbl,
                            DamNhiemEntryTbl, DieuKhoanEntryTbl, DieuKhoanLaoDongEntryTbl,
                            DoiTacEntryTbl, GioiThieuEntryTbl, HangHoaEntryTbl,
                            HDDD_DKEntryTbl, HoaDonEntryTbl, HoiDongQuanTriEntryTbl,
                            HopDongEntryTbl, KhachHangEntryTbl, LuongEntryTbl, NhapHangEntryTbl,
                            PhuCapEntryTbl, ThanhVienHDQTEntryTbl)
                }
                Thread.currentThread().interrupt()
                Platform.runLater{
                    close()
                    TransactionManager.closeAndUnregister(controller.connect!!)
                    find(ApplicationWorkspace::class).openModal()
                }
            }
        }
    }
}



