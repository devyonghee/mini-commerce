package me.devyonghee.common.persistence

import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.LocalDateTime


@MappedSuperclass
abstract class AuditEntity {
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @CreatedBy
    lateinit var createdBy: String

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    @LastModifiedBy
    lateinit var updatedBy: String
}