package com.yhogie.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Response(
    @field:SerializedName("results")
    val results : List<MovieResponse>
)


