package com.example.vehicle_shop_clean_refactor._unsorted

interface Consumer<T> {
    fun consume(data: ConsumerData<T>)
}
