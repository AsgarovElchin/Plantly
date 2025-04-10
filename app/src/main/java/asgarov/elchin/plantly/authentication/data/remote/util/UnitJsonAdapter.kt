package asgarov.elchin.plantly.authentication.data.remote.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException

object MoshiUnitAdapter : JsonAdapter<Unit>() {

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Unit {
        reader.skipValue()
        return Unit
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Unit?) {
        writer.nullValue()
    }
}