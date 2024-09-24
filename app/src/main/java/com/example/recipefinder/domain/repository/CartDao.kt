package com.example.recipefinder.domain.repository

import androidx.room.*
import com.example.recipefinder.domain.models.CartItem

@Dao
interface CartDao {

        @Query("SELECT * FROM item_table")
        fun getAll(): List<CartItem>

        /* @Query("SELECT * FROM student_table WHERE uid IN (:userIds)")
         fun loadAllByIds(userIds: IntArray): List<Student>*/

      /*  @Query("SELECT * FROM item_table WHERE id LIKE :id LIMIT 1")
        suspend fun findByRoll(id: Int): CartItem*/

        @Insert(onConflict = OnConflictStrategy.IGNORE)
         fun insert(item: CartItem)

        @Delete
         fun delete(cartItem: CartItem)

        @Query("DELETE FROM item_table")
         fun deleteAll()


    }


