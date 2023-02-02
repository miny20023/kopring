package mytest.kopring.service

import mytest.kopring.dto.userInfo.FindUserInfoResDTO
import mytest.kopring.dto.userInfo.InsertUserInfoResDTO
import mytest.kopring.dto.userInfo.UpdateUserInfoResDTO
import mytest.kopring.entity.UserInfo
import mytest.kopring.repository.UserRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    // 다중 조회 된 유저 정보 배열로 반환
    fun readUsers() : List<UserInfo> {
//        val targetUsers = userRepository.findAllBy()
        val targetUsers = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
        return targetUsers.map{it}
    }

    // email과 pw로 단일 유저 정보 반환
    fun readUser(findUserInfoResDTO: FindUserInfoResDTO) : UserInfo? {
        return userRepository.findByEmailAndPw(findUserInfoResDTO.email, findUserInfoResDTO.pw)
    }

    // 유저 정보 받아와서 DB데이터 추가
    fun createUser(insertUserInfoResDTO: InsertUserInfoResDTO): UserInfo {
        return userRepository.save(insertUserInfoResDTO.toEntity())
    }

    // 유저 정보 받아와서 DB데이터 삭제
    fun deleteUser(findUserInfoResDTO: FindUserInfoResDTO): String{
        val targetUser = userRepository.findByEmailAndPw(findUserInfoResDTO.email, findUserInfoResDTO.pw)
        return if(targetUser !== null) {
            userRepository.delete(targetUser)
            "Delete action Success"
        } else "Delete action Fail"
    }

    // 유저 id와 수정데이터 받아와서 해당 id가진 DB데이터 수정
    fun updateUser(id: Long, updateUserInfoResDTO: UpdateUserInfoResDTO): String {
        val targetUser = userRepository.findById(id)
        return if(targetUser.isEmpty) { "Update action Fail : because id(=${id}) is not found" }
        else {
            userRepository.save(updateUserInfoResDTO.toEntity(id))
            "Update action success"
        }
    }
}