package me.devyonghee.commerce.member.persistence

import me.devyonghee.commerce.member.domain.Member
import me.devyonghee.commerce.member.domain.MemberRepository
import me.devyonghee.commerce.member.persistence.jpa.MemberEntity
import me.devyonghee.commerce.member.persistence.jpa.MemberEntityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class MemberDao(
    private val jpaRepository: MemberEntityRepository
) : MemberRepository {

    override fun save(member: Member): Member {
        return jpaRepository.save(MemberEntity(member)).toDomain()
    }

    override fun findById(id: Long): Member? {
        return jpaRepository.findByIdOrNull(id)?.toDomain()
    }
}