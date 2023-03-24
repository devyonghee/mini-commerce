package me.devyonghee.commerce.member.application

import me.devyonghee.commerce.member.domain.Member
import me.devyonghee.commerce.member.domain.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun create(member: Member) {
        memberRepository.save(member)
    }
}