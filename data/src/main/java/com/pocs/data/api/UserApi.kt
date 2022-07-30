package com.pocs.data.api

import com.pocs.data.model.ResponseBody
import com.pocs.data.model.user.UserDto
import com.pocs.data.model.user.UserListDto
import com.pocs.data.model.user.UserListSortingMethodDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun getAll(
        @Query("sort") sort: UserListSortingMethodDto
    ): ResponseBody<UserListDto>

    @GET("users/{userId}")
    suspend fun getUserDetail(
        @Path("userId") id: Int
    ): ResponseBody<UserDto>
}