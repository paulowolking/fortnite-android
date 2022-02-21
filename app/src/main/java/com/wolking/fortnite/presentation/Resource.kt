package com.wolking.fortnite.presentation

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import retrofit2.Response


sealed class Resource<out T> {

//    companion object {
//        fun <T> create(error: Throwable): Failure<T> {
//            return Failure(error)
//        }
//
//        fun <T> create(response: Response<T>): Resource<T> {
//            return if (response.isSuccessful) {
//                val body = response.body()
//                if (body == null || response.code() == 204) {
//                    Empty()
//                } else {
//                    Success(body)
//                }
//            } else {
//                val msg = response.errorBody()?.string()
//                val errorMsg = if (msg.isNullOrEmpty()) {
//                    response.message()
//                } else {
//                    try {
//                        val json: JsonObject = JsonParser().parse(msg) as JsonObject
//                        if (json.has("message") && json.has("errors")) {
//                            val errorObject = json.get("errors").asJsonObject
//                            val fields = errorObject.keySet()
//                            val errors = fields.map {
//                                errorObject.get(it).asJsonArray.joinToString("\n")
//                            }
//                            errors.joinToString("\n")
//                        } else if (json.has("message")) {
//                            json.get("message").asString
//                        } else {
//                            msg
//                        }
//                    } catch (e: Exception) {
//                        "Erro na resposta do servidor"
//                    }
//                }
//                Failure(Throwable(errorMsg))
//            }
//        }
//    }

    class Loading<out T> : Resource<T>()

    //    class Empty<T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val throwable: Throwable) : Resource<T>()

}