package com.example.recipefinder.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class CartItem(

    @PrimaryKey val id: String,
    @ColumnInfo(name = "item_name") val itemName: String?,
    @ColumnInfo(name = "price") val price: Long,
    //@ColumnInfo(name = "quantity") val quantity:Int?,
    @ColumnInfo(name = "image") val image:String?
)

data class OrderItem(

    @PrimaryKey val id: String,
    @ColumnInfo(name = "item_name") val itemName: String?,
    @ColumnInfo(name = "price") val price: Long,
)