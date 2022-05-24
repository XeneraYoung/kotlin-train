package util

open class ResponseError(
    override val message: String,
    val code: Int,
    val field: String? = null
) : Exception(message)

class MissingFieldError(message: String?) : ResponseError(
    code = 400,
    message = message ?: "Request body missing required field."
)

class BadFieldError(message: String, field: String) : ResponseError(
    code = 400,
    message = message,
    field = field
)

class BadRequestError(message: String) : ResponseError(
    code = 400,
    message = message
)

class IncompatibleAvatarError(message: String) : ResponseError(
    code = 400,
    message = message
)

class UnknownFieldError(message: String?) : ResponseError(
    code = 400,
    message = message ?: "Request body contains unknown field."
)

class JsonDataError : ResponseError(
    code = 400,
    message = "Request body JSON is incorrect."
)

class NotFoundError(message: String) : ResponseError(
    code = 404,
    message = message
)

class DuplicateError(message: String, field: String) : ResponseError(
    code = 400,
    message = message,
    field = field
)

class ResourceInUseError(message: String) : ResponseError(
    code = 400,
    message = message
)

class ServerError(message: String) : ResponseError(
    code = 500,
    message = message
)

class BadAgentDefinitionError(message: String) : ResponseError(
    code = 400,
    message = message
)

class AuthorizationError(message: String = "Not authorized to access this endpoint.") : ResponseError(
    code = 401,
    message = message
)
