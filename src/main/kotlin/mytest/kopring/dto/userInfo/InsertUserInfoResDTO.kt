package mytest.kopring.dto.userInfo

import mytest.kopring.entity.UserInfo

data class InsertUserInfoResDTO (
    val email: String,
    val pw: String,
    val name: String,
    val phonenumber: Long,
    val etc: String?
) {
    fun toEntity() : UserInfo {
        return UserInfo(
            id = null,
            email = this.email,
            pw = this.pw,
            name = this.name,
            phonenumber = this.phonenumber,
            etc = this.etc
        )
    }
}