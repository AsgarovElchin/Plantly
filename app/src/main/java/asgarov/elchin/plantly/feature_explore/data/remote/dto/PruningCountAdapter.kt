package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.*

class PruningCountAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): PruningCountDto {
        return when (reader.peek()) {
            JsonReader.Token.BEGIN_OBJECT -> {
                val adapter = Moshi.Builder().build().adapter(PruningCountData::class.java)
                adapter.fromJson(reader) ?: PruningCountEmpty
            }

            JsonReader.Token.BEGIN_ARRAY -> {
                reader.beginArray()
                if (reader.hasNext()) {
                    reader.skipValue()
                }
                reader.endArray()
                PruningCountEmpty
            }

            else -> {
                reader.skipValue()
                PruningCountEmpty
            }
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: PruningCountDto?) {
        // Not used; skip serialization
        throw UnsupportedOperationException("Serialization not supported")
    }
}