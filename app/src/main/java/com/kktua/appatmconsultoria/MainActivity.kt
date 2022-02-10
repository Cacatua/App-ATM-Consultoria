package com.kktua.appatmconsultoria

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kktua.appatmconsultoria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        configureFloatingActionButton()

        configureAppBar()
    }

    private fun configureFloatingActionButton() {
        binding.appBarMain.fab.setOnClickListener {
            sendEmail()
        }
    }

    private fun sendEmail() {
        intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, "teste@teste.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo app")
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem automatica")
        intent.type = "message/rfc822"
        startActivity(intent)
    }

    private fun configureAppBar() {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_main, R.id.nav_services, R.id.nav_clients, R.id.nav_contact, R.id.nav_about
            ), binding.drawerLayout
        )

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}