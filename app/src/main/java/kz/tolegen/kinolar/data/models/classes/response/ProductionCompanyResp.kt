package kz.tolegen.kinolar.data.models.classes.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ProductionCompanyResp(
    @Json(name = "id") val id: Long,
    @Json(name = "logo_path") val logo_path: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "origin_country") val origin_country: String?
) : Parcelable