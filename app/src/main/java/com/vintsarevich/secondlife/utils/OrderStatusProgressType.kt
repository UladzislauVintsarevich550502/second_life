package com.vintsarevich.secondlife.utils

import android.graphics.Color

enum class OrderStatusProgressType(val orderStatusProgress: OrderStatusProgress) {
    ORDER_ARCHIVED(OrderStatusProgress(100f, Color.RED)),
    ORDER_COMPLETE(OrderStatusProgress(100f, Color.GREEN)),
    ORDER_SUBMIT_TO_LAB(OrderStatusProgress(100f, Color.YELLOW)),
    ORDER_START(OrderStatusProgress(30f, Color.GREEN)),
    DEFAULT(OrderStatusProgress(0f, Color.GREEN))
}