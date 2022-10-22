package com.yongpang.common.domain

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractTimestampEntity(
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private val createdAt: Instant = Instant.now(),

    @CreatedBy
    @Column(updatable = false)
    private val createdBy: String? = null,

    @LastModifiedDate
    @Column(nullable = false)
    private var updatedAt: Instant = Instant.now(),

    @Column
    @LastModifiedBy
    private var updatedBy: String? = null
) {
}
