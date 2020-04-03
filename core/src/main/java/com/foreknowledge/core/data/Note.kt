package com.foreknowledge.core.data

class Note (
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0L
)