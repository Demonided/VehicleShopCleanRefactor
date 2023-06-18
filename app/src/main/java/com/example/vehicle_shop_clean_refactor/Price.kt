package com.example.vehicle_shop_clean_refactor

data class Price(val value: Double, val currency: Currency) {
    constructor(value: Int, currency: Currency) : this(value.toDouble(), currency)
}
