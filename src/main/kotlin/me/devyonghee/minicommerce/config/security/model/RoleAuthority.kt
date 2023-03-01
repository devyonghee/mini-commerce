package me.devyonghee.minicommerce.config.security.model

import me.devyonghee.minicommerce.member.domain.Role
import org.springframework.security.core.GrantedAuthority

enum class RoleAuthority(
    private val role: Role
) : GrantedAuthority {
    ROLE_CUSTOMER(Role.CUSTOMER),
    ROLE_ADMIN(Role.ADMIN),
    ;

    override fun getAuthority(): String {
        return name
    }

    companion object {
        fun valueOf(role: Role): RoleAuthority {
            return values().firstOrNull { it.role == role }
                ?: throw IllegalArgumentException("rule authority not found for role($role)")
        }
    }
}