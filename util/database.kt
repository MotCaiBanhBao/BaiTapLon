package luongvany.k12tt.util

import luongvany.k12tt.model.datamodel.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

private var LOG_TO_CONSOLE: Boolean = false

//Thêm phần hiển thị câu lệnh trên cmd
fun newTransaction(): Transaction = TransactionManager.currentOrNew(Connection.TRANSACTION_SERIALIZABLE).apply {
    if(LOG_TO_CONSOLE) addLogger(StdOutSqlLogger)
}

fun enableConsoleLogger(){
    LOG_TO_CONSOLE = true
}

fun createTables(){
    enableConsoleLogger()
    execute {
        SchemaUtils.create(StaffEntryTbl, DepartmentEntryTbl, ChucVuEntryTbl,
                DamNhiemEntryTbl, DieuKhoanEntryTbl, DieuKhoanLaoDongEntryTbl,
                DoiTacEntryTbl, GioiThieuEntryTbl, HangHoaEntryTbl,
                HDDD_DKEntryTbl, HoaDonEntryTbl, HoiDongQuanTriEntryTbl,
                HopDongEntryTbl, KhachHangEntryTbl, LuongEntryTbl, NhapHangEntryTbl,
                PhuCapEntryTbl, ThanhVienHDQTEntryTbl)
    }
}
// Môi trường thực hiện các lệnh command sql
fun <T> execute(command: () -> T): T{
    with(newTransaction()){
        return command().apply {
            commit()
            close()
        }
    }
}
fun isInit(): Boolean{
    try {
        execute {
            TransactionManager.current().exec("use test;")
        }
    }catch (ex: Exception){
        return false
    }
    return true
}

fun isConnected(): Boolean{
    try {
        execute {
            TransactionManager.current().exec("Show databases;")
        }
    }catch (ex: Exception) {
        return false
    }
    return true
}
