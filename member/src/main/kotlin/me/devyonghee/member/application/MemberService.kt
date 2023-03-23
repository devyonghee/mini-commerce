package me.devyonghee.member.application

import me.devyonghee.member.domain.Member
import me.devyonghee.member.domain.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun create(member: Member) {
        memberRepository.save(member)
    }
}