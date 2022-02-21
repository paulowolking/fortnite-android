package com.wolking.fortnite.data.remote.util


import com.google.gson.JsonObject
import com.google.gson.JsonParser
import retrofit2.HttpException
import retrofit2.Response


/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {

    companion object {

        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(0, error.message ?: "unknown error")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body)
                }
            } else {
                val message = response.errorBody()?.string()
                ApiErrorResponse(response.code(), message ?: "Erro desconhecido")
            }
        }

        fun handlerError(throwable: Throwable) : String {
            val httpException = throwable as? HttpException
            val response = httpException?.response()
            val message = response?.errorBody()?.string()
            return interpreteMessage(message)
        }

        fun interpreteMessage(message: String?): String {
            return if (message.isNullOrEmpty()) {
                ""
            } else {
                try {
                    val json: JsonObject = JsonParser().parse(message) as JsonObject
                    if (json.has("message") && json.has("errors")) {
                        val errorObject = json.get("errors").asJsonObject
                        val fields = errorObject.keySet()
                        val errors = fields.map {
                            errorObject.get(it).asJsonArray.joinToString("\n")
                        }
                        errors.joinToString("\n")
                    } else if (json.has("message")){
                        json.get("message").asString
                    } else {
                        message
                    }
                } catch (e: Exception) { "Falha ao processar requisição" }
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val code: Int,val errorMessage: String) : ApiResponse<T>()

