package dev.ayu.latte.ratelimit

import com.squareup.moshi.JsonClass

object SharedRatelimitData {

    const val MATCHA_CLUSTER_ID = -1

    const val RATELIMIT_TOPIC = "ratelimit"

    const val KEY_REQUEST_IDENTIFY_QUOTA = "request-identify-quota"
    const val KEY_REQUEST_GLOBAL_QUOTA = "request-global-quota"

    @JsonClass(generateAdapter = true)
    class RatelimitClientData

}
