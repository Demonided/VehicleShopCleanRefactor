package com.example.vehicle_shop_clean_refactor._unsorted

import com.example.vehicle_shop_clean_refactor.Price
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object PriceFormatter {

    fun formatPrice(price: Price): String {
        val decimalFormat = DecimalFormat()
        decimalFormat.isDecimalSeparatorAlwaysShown = false
        decimalFormat.groupingSize = 3
        decimalFormat.maximumFractionDigits = 2

        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = ' '

        decimalFormat.decimalFormatSymbols = decimalFormatSymbols

        val priceValue = decimalFormat.format(price.value)

        return "$priceValue ${price.currency.symbol}"
    }
}
