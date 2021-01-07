package com.rebel.foodrunner.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey val id:String,
    @ColumnInfo(name = "res_name") val name: String,
    @ColumnInfo(name = "res_price") val price: String,
    @ColumnInfo(name = "res_rating") val rating: String,
    @ColumnInfo(name = "res_image") val image:String
) {
}