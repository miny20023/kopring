package mytest.kopring.controller

import mytest.kopring.dto.userInfo.FindUserInfoResDTO
import mytest.kopring.dto.userInfo.InsertUserInfoResDTO
import mytest.kopring.dto.userInfo.UpdateUserInfoResDTO
import mytest.kopring.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("http://localhost:3000", "http://114.205.236.79:3000")
@RestController
class UserController(
    var userService: UserService
) {
    // 유저 다중 조회
    @GetMapping("/user/readUsers")
    fun readUsersInfo(): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.readUsers())
    }

    // 유저 단일 조회
    @PostMapping("/user/readUser")
    fun readUserInfo(@RequestBody findUserInfoResDTO: FindUserInfoResDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(userService.readUser(findUserInfoResDTO))
    }

    // 유저 단일 생성
    @PostMapping("/user/createUser")
    fun createUserInfo(@RequestBody insertUserInfoResDTO: InsertUserInfoResDTO): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.createUser(insertUserInfoResDTO))
    }

    // 유저 단일 삭제
    // "/user/deleteUser/{email}/{pw}"로 맵핑해서 @PathVariable로 email과 pw를 받아서 할 수도 있음.
    // 현재 방법은 유저 단일 조회에서 DTO 재활용했다는 가정하에 진행
    @DeleteMapping("/user/deleteUser")
    fun deleteUserInfo(@RequestBody findUserInfoResDTO: FindUserInfoResDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(userService.deleteUser(findUserInfoResDTO))
    }

    // 유저 단일 수정
    @PutMapping("/user/updateUser/{id}")
    fun updateUserInfo(@PathVariable("id") id: Long, @RequestBody updateUserInfoResDTO: UpdateUserInfoResDTO): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.updateUser(id, updateUserInfoResDTO))
    }
}