package com.example.vehicle_shop_clean.data.mapper

import com.example.vehicle_shop_clean.domain.model.currency.Currency
import com.example.vehicle_shop_clean.domain.model.currency.Rate

object CurrencyRateMapper {

    fun map(mainCurrency: Currency, rateDto: Map<String, Double>): List<Rate> {
        return rateDto.filterKeys { isSupported(it) }.mapNotNull { (abbr, rateValue) ->
            getCurrencyByAbbr(abbr)?.let { currency ->
                Rate(mainCurrency, currency, rateValue)
            }
        }
    }

    private fun isSupported(abbr: String): Boolean {
        return Currency.values().map(Currency::abbr).contains(abbr)
    }

    private fun getCurrencyByAbbr(abbr: String): Currency? {
        return Currency.values().find { it.abbr == abbr }
    }
}
