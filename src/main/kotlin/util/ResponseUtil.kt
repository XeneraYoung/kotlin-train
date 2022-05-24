package util

import javax.ws.rs.core.GenericEntity
import javax.ws.rs.core.Response

inline fun <reified T> Response.ResponseBuilder.genericEntity(entity: T): Response.ResponseBuilder {
    return this.entity(object : GenericEntity<T>(entity) {})
}