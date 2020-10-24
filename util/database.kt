package luongvany.k12tt.util

import luongvany.k12tt.model.DepartmentEntryTbl
import luongvany.k12tt.model.StaffEntryTbl
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
    with(newTransaction()){
        SchemaUtils.create(StaffEntryTbl, DepartmentEntryTbl)
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
