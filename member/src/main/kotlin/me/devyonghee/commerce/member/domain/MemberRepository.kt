package me.devyonghee.commerce.member.domain

interface MemberRepository {

    fun save(member: Member): Member

    fun findById(id: Long): Member?
}