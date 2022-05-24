package dao

import org.jetbrains.exposed.sql.Table

object DepTable : Table("dep_t") {
    val id = integer("id").autoIncrement()
    val name = text("name")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
