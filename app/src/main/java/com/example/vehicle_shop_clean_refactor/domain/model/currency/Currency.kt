package com.example.vehicle_shop_clean_refactor.domain.model.currency

enum class Currency(val abbr: String, val symbol: Char) {
    RUBLE("RUB", '\u20BD'),
    EURO("EUR", '\u20AC'),
    DOLLAR("USD", '\u0024'),
    YUAN("CNY", '\u00A5')
}
