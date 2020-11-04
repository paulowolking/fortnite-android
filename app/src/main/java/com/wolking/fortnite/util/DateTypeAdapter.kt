package com.wolking.fortnite.util
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Arrays
import java.util.Date
import java.util.Locale

class DateTypeAdapter : JsonDeserializer<Date>, JsonSerializer<Date> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        jsonElement: JsonElement, typeOF: Type,
        context: JsonDeserializationContext
    ): Date {
        for (format in DATE_FORMATS) {
            try {
                return SimpleDateFormat(format, Locale.getDefault()).parse(jsonElement.asString)
            } catch (e: ParseException) {
            }
        }
        throw JsonParseException(
            "Unparseable date: \"" + jsonElement.asString
                    + "\". Supported formats: " + Arrays.toString(DATE_FORMATS)
        )
    }

    override fun serialize(
        src: Date,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        val value = SimpleDateFormat(DATE_FORMATS[0], Locale.getDefault()).format(src)
        return JsonPrimitive(value)
    }

    companion object {
        private val DATE_FORMATS = arrayOf("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm")
    }
}
