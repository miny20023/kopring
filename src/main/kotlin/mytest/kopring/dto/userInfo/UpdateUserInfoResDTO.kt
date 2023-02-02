package mytest.kopring.dto.userInfo

import mytest.kopring.entity.UserInfo

data class UpdateUserInfoResDTO (
    val email: String,
    val pw: String,
    val name: String,
    val phonenumber: Long,
    val etc: String?
) {
    fun toEntity(id: Long) : UserInfo {
        return UserInfo(
            id = id,
            email = this.email,
            pw = this.pw,
            name = this.name,
            phonenumber = this.phonenumber,
            etc = this.etc
        )
    }
}