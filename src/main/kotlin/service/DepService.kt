package service

import dao.DepTable
import entity.DepEntity
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class DepService() {
    fun getAll(): List<DepEntity> {
        return transaction {
            DepTable.selectAll().map {
                DepEntity(
                    id = it[DepTable.id],
                    name = it[DepTable.name]
                )
            }
        }
    }
}