package mytest.kopring.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "user_info")
data class UserInfo (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    val email: String,
    val pw: String,
    val name: String?,
    val phonenumber: Long?,
    val etc: String?
) {

}