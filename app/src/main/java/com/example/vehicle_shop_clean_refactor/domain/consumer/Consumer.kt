package com.example.vehicle_shop_clean_refactor.domain.consumer

interface Consumer<T> {
    fun consume(data: ConsumerData<T>)
}
