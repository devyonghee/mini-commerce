package me.devyonghee.minicommerce.member.application

import me.devyonghee.minicommerce.member.domain.Member
import me.devyonghee.minicommerce.member.domain.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun member(id: Long): Member {
        return memberRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("member(id: '$id') not found")
    }
}
