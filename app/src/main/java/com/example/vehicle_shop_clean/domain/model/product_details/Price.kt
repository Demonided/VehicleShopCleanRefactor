package com.example.vehicle_shop_clean.domain.model.product_details

import com.example.vehicle_shop_clean.domain.model.currency.Currency

data class Price(val value: Double, val currency: Currency) {
    constructor(value: Int, currency: Currency) : this(value.toDouble(), currency)
}
