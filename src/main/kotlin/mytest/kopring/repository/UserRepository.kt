package mytest.kopring.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import mytest.kopring.entity.QUserInfo.userInfo
import mytest.kopring.entity.UserInfo
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserInfo, Long>, UserCustomRepository {
//    fun findAllBy(): List<UserInfo>
}

interface UserCustomRepository {
//    fun findById(id: Long): UserInfo?
    fun findByEmailAndPw(email: String, pw: String): UserInfo?
}

class UserCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
): UserCustomRepository {
//    override fun findById(id: Long): UserInfo? {
//        return queryFactory
//            .selectFrom(userInfo)
//            .where(
//                userInfo.id.eq(id)
//            )
//            .fetchOne()
//    }

    override fun findByEmailAndPw(email: String, pw: String): UserInfo? {
        return queryFactory
            .selectFrom(userInfo)
            .where(
                userInfo.email.eq(email),
                userInfo.pw.eq(pw)
            )
            .fetchOne()
    }
}