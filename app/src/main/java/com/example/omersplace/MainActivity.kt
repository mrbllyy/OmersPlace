package com.example.omersplace

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.res.loader.ResourcesLoader
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.omersplace.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homePageFragment, R.id.cartFragment, R.id.moreOptionsFragment
            )
        )

        val navView: BottomNavigationView = binding.bottomNav

        setSupportActionBar(binding.toolbarMain)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (!checkForInternetConnection(this)) {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Warning!")
                .setMessage("Check your internet connection and try again later!")
                .setView(R.layout.warning)
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                    Log.e("OmersPlace", "$dialogInterface")
                    Log.e("OmersPlace", "$i")
                    super.onBackPressed()
                })
            alertDialog.show()
        }

        setContentView(binding.root)

    }

    //This function is required to open the alert dialog when there is no internet connection.
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkForInternetConnection(context: Context): Boolean {
        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    }


}