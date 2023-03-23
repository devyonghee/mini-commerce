package me.devyonghee.member.persistence

import me.devyonghee.member.domain.Member
import me.devyonghee.member.domain.MemberRepository
import me.devyonghee.member.persistence.jpa.MemberEntity
import me.devyonghee.member.persistence.jpa.MemberEntityRepository
import org.springframework.data.repository.findByIdOrNull

class MemberDao(
    private val jpaRepository: MemberEntityRepository
) : MemberRepository {

    override fun save(member: Member): Member {
        return jpaRepository.save(MemberEntity(member)).toDomain()
    }

    override fun findById(id: Long): Member? {
        return jpaRepository.findByIdOrNull(id)?.toDomain()
    }
}