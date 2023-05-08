package com.khaliddev0.mystcassignment.data.model

import com.google.gson.annotations.SerializedName
import com.khaliddev0.mystcassignment.domain.user.model.User

data class UserDto(

	@field:SerializedName("per_page")
    val perPage: Int,

	@field:SerializedName("total")
    val total: Int,

    @field:SerializedName("data")
    val UserDataItem: List<UserDataItem>,

	@field:SerializedName("page")
    val page: Int,

	@field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("support")
    val support: Support
)

data class UserDataItem(

    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("avatar")
    val avatar: String,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("email")
    val email: String
)

data class Support(

    @field:SerializedName("text")
    val text: String,

    @field:SerializedName("url")
    val url: String
)

fun UserDataItem.toUser() = User(
    id = this.id,
    fullName = this.firstName + " " + this.lastName,
    email = this.email,
    imageUrl = this.avatar,
)
