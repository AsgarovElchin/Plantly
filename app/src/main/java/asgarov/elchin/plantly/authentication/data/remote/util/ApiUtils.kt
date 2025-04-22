package asgarov.elchin.plantly.authentication.data.remote.util

import com.squareup.moshi.Types
import java.lang.reflect.Type

inline fun <reified T> parameterizedTypeOf(): Type {
    return Types.newParameterizedType(
        asgarov.elchin.plantly.authentication.data.remote.dto.ApiResponseDto::class.java, T::class.java
    )
}