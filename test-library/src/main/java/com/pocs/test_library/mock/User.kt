package com.pocs.test_library.mock

import com.pocs.domain.model.post.PostWriter
import com.pocs.domain.model.user.User
import com.pocs.domain.model.user.UserType

val mockPostWriter1 = PostWriter(1, "kim", "jja0i213@naver.com", UserType.MEMBER)

val mockNormalUser = User(1, "권김정", 1971034, 30, "-")
val mockKickedUser = User(1, "권김정", 1971034, 30, "2021-02-12")