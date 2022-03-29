package kz.tolegen.kinolar.data.models.classes.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class GenreResp(
    @Json(name = "id")val id: Long,
    @Json(name = "name")val name: String
) : Parcelable