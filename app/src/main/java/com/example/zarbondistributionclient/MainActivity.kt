package com.example.zarbondistributionclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var graph: NavGraph
    private lateinit var host: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        host = myNavHostFragment as NavHostFragment
        graph = host.navController.navInflater.inflate(R.navigation.navigation)

        graph.startDestination = R.id.loginFragment
        host.navController.graph = graph
    }

    override fun onBackPressed() {

        when(NavHostFragment.findNavController(host).currentDestination?.id) {
            R.id.homeFragment-> {
                AlertDialog.Builder(this)
                    .setTitle("Diqqat!")
                    .setMessage("Tizimdan chiqmoqchimisiz!")
                    .setNegativeButton("Yo'q") { dialog, _ ->
                        dialog.cancel()
                    }
                    .setPositiveButton("Ha") { dialog, _ ->
                        super.onBackPressed()
                        dialog.cancel()
                    }.show()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}