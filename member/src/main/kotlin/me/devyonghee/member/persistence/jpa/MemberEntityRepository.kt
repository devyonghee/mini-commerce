package me.devyonghee.member.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface MemberEntityRepository : JpaRepository<MemberEntity, Long> {
}