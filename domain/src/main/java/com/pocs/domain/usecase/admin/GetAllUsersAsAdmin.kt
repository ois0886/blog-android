package com.pocs.domain.usecase.admin

import com.pocs.domain.repository.AdminRepository
import javax.inject.Inject

class GetAllUsersAsAdmin @Inject constructor(
    private val repository: AdminRepository
) {
    operator fun invoke() = repository.getAllUsers()
}