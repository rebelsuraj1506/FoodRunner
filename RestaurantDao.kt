package com.rebel.foodrunner.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RestaurantDao {
    @Insert
    fun insertRestaurant(resEntity: RestaurantEntity)
    @Delete
    fun deleteRestaurant(resEntity: RestaurantEntity)
    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants():List<RestaurantEntity>
    @Query("SELECT * FROM restaurants where id=:resId")
    fun getRestaurantByID(resId: String):RestaurantEntity
}