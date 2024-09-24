package com.example.recipefinder.data.mbrojtja

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.room.Transaction
import com.example.recipefinder.data.test.player
import com.example.recipefinder.data.test.team
import com.example.recipefinder.data.test.ubtDatabase
import com.example.recipefinder.databinding.FragmentTestBinding

class TestFragment : Fragment() {

    lateinit var binding: FragmentTestBinding
    lateinit var appDb: ubtDatabase

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(
            layoutInflater,
            container, false
        )

        appDb = ubtDatabase.getDatabase(requireContext())


/*

        appDb.testDao().insertUser(appUsers(123, "Blini"))
        appDb.testDao().insertUser(appUsers(456, "Mujka"))
*/

        //  appDb.testDao().insertTransaction(transaction(1454, 456))


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.readBttn.setOnClickListener {
            println("Read by number")
            println(appDb.playerDao().getPlayerByNumber(30))

            //insert team
            binding.createBttn.setOnClickListener {
                appDb.playerDao().insertTeam(team(1, "FC Barcelona"))
                appDb.playerDao().insertTeam(team(2, "Paris Saint Germain"))
            }
            binding.insertPlayer.setOnClickListener {


                var playerList = listOf(
                    player(1, "Lionel Messi", 30, 1),
                    (player(2, "Neymar Jr", 10, 2)),
                    (player(3, " Kylian Mbappe", 7, 2)),
                    (player(4, "Segio Busqests", 5, 1)),
                    (player(1, "Gerard Pique", 3, 1)),
                    (player(4, "Marco Verrati", 6, 2))
                )

                for (player in playerList){
                    appDb.playerDao().insertPlayer(player)
                }
            }

            binding.updateBttn.setOnClickListener {
                appDb.playerDao().update(team(1,"Ac Milan"))
            }

            binding.getByNumber.setOnClickListener {
               var teamId=  binding.inputtxt.text.toString()
                var players = appDb.playerDao().getByTeam(teamId.toLong())
                println(players.toString())
            }

           /*   println("test one to many")
            println(appDb.testDao().getAppUsersWithTransactions())
        }*//*
            binding.updateBttn.setOnClickListener {
                appDb.testDao().update(appUsers(14242323, "Blini Mujka"))
            }

        }*/
        }

    }
}