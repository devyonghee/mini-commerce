package me.devyonghee.minicommerce.auth.domain

import jakarta.persistence.*
import me.devyonghee.minicommerce.common.domain.Email

@Entity
class EmailPasswordAccount(

    @Embedded
    @AttributeOverrides(AttributeOverride(name = "email", column = Column(nullable = false, unique = true)))
    val email: Email,
    val password: String,
    val memberId: Long,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

}