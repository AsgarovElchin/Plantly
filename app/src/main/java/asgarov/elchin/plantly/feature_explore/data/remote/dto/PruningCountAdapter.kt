package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.*

class PruningCountAdapter(
    private val dataAdapter: JsonAdapter<PruningCountData>
) {
    @FromJson
    fun fromJson(reader: JsonReader): PruningCountDto {
        return try {
            when (reader.peek()) {
                JsonReader.Token.BEGIN_OBJECT -> {
                    dataAdapter.fromJson(reader) ?: PruningCountEmpty
                }

                JsonReader.Token.BEGIN_ARRAY -> {
                    reader.beginArray()
                    while (reader.hasNext()) reader.skipValue()
                    reader.endArray()
                    PruningCountEmpty
                }

                JsonReader.Token.NULL -> {
                    reader.nextNull<Unit>()
                    PruningCountEmpty
                }

                else -> {
                    reader.skipValue()
                    PruningCountEmpty
                }
            }
        } catch (e: Exception) {
            PruningCountEmpty
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: PruningCountDto?) {
        throw UnsupportedOperationException("Serialization not supported")
    }
}

