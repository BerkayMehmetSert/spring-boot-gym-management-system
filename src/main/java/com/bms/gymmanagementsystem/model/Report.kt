package com.bms.gymmanagementsystem.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime

@Entity
data class Report @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = null,

    val date: LocalDateTime,

    val totalAmount:Double,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "client_id")
    val client: Client,

    @ManyToOne(fetch=FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "transaction_id")
    val transaction: Transaction
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Report

        if (id != other.id) return false
        if (date != other.date) return false
        if (totalAmount != other.totalAmount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + date.hashCode()
        result = 31 * result + totalAmount.hashCode()
        return result
    }
}
