package me.devyonghee.minicommerce.common.domain

import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant


@MappedSuperclass
abstract class AuditEntity {
    @CreatedDate
    lateinit var createdAt: Instant

    @CreatedBy
    lateinit var createdBy: String

    @LastModifiedDate
    lateinit var updatedAt: String

    @LastModifiedBy
    lateinit var updatedBy: String
}