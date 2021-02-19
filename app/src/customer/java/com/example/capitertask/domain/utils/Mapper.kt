package com.example.capitertask.domain.utils

abstract class Mapper<in E,T> {
    abstract fun mapFrom(from:E):T
}