package com.example.unsplash.utils

import com.example.unsplash.BuildConfig

object Constant {
    const val BASE_URL = "https://api.unsplash.com"
    const val API_KEY = BuildConfig.API_KEY
    const val DEFAULT_PAGE = 1
    const val DEFAULT_ID = ""
    const val API_CLIENT_ID = "client_id"
    const val DATABASE_NAME = "unsplash"
    const val DEFAULT_ITEM = 10
    const val RANDOM_ITEM_COUNT = 5
    const val DEFAULT_STRING = ""
}

object ErrorMessage {
    const val RATE_LIMIT_EXCEEDED = "retrofit2.HttpException: HTTP 403 "
    const val NO_NETWORK_CONNECTION = "retrofit2.HttpException: HTTP 401 "
}
