package me.devyonghee.member.persistence.jpa

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import me.devyonghee.common.domain.Email
import me.devyonghee.member.domain.Address
import me.devyonghee.member.domain.Member
import me.devyonghee.member.domain.PhoneNumber

@Entity
class MemberEntity(
    var name: String,
    var email: String,

    val address: String,
    val addressDetail: String,
    val postCode: String,

    var phoneNumber: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {

    constructor(member: Member) : this(
        member.name,
        member.email.toString(),
        member.address.address,
        member.address.addressDetail,
        member.address.postCode,
        member.phoneNumber.toString(),
        member.id
    )

    fun toDomain(): Member {
        return Member(
            name,
            Email(email),
            Address(address, addressDetail, postCode),
            PhoneNumber(phoneNumber),
            id
        )
    }
}