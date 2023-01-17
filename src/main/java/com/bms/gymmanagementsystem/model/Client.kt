package com.bms.gymmanagementsystem.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
data class Client @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = null,

    val firstName: String,

    val lastName: String,

    val age: Int,

    @Enumerated(EnumType.STRING)
    val gender: Gender,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id")
    val address: Address,

    val email: String,

    val phoneNumber: String,

    val password: String,

    @OneToMany(mappedBy = "client")
    val memberships: Set<Membership>? = HashSet(),

    @OneToMany(mappedBy = "client")
    val payments: Set<Payment>? = HashSet(),

    @OneToMany(mappedBy = "client")
    val schedules: Set<Schedule>? = HashSet(),

    @OneToMany(mappedBy = "client")
    val transactions: Set<Transaction>? = HashSet(),

    @OneToMany(mappedBy = "client")
    val reports: Set<Report>? = HashSet()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (age != other.age) return false
        if (gender != other.gender) return false
        if (address != other.address) return false
        if (email != other.email) return false
        if (phoneNumber != other.phoneNumber) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + age
        result = 31 * result + gender.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}
