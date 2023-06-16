package com.example.vehicle_shop_clean_refactor.domain.model.product_details

import com.example.vehicle_shop_clean_refactor.domain.model.currency.Currency

data class Price(val value: Double, val currency: Currency) {
    constructor(value: Int, currency: Currency) : this(value.toDouble(), currency)
}
