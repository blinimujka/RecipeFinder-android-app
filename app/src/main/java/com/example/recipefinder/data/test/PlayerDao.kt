package com.example.recipefinder.data.test

import android.support.annotation.RequiresPermission.Read
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update


@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTeam(team: team )

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPlayer(player: player)

    @Query("Select * From player where Number= :number")
    fun getPlayerByNumber(number:Long) : List<player>

    @Update
    fun update(team: team)


    @Query("SELECT * FROM player Where teamId=:teamId")
    fun getByTeam(teamId: Long) :List<TeamsWithPlayers>
}