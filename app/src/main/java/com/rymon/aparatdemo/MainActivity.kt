package com.rymon.aparatdemo

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.AutoCompleteTextView
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rymon.aparatdemo.application.AparatDemoApplication
import com.rymon.aparatdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)
        setupInitializeNavigation()
        setupInitializeToolbar()
        setupInitializeBottomNavigation()

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        binding.apply {
            /* userButton.setOnClickListener{
                 val action = NavGraphDirections.actionGlobalLoginFragment()
                 navController.navigate(action)

             }*/
        }

    }


    private fun setupInitializeBottomNavigation() {

        binding.bottomNav.setupWithNavController(navController)
    }

    private fun setupInitializeToolbar() {

        setSupportActionBar(toolbar)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    private fun setupInitializeNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.TestFragment,R.id.searchFragment, R.id.mainHomeFragment)
        )


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        /**
         *We can have below approach but it's not shoiwing animate and also
         * we can't call login from any where else
         */
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

        /**
         *intstead of thet we use a global destination
         */
        return when (item.itemId) {
            R.id.loginFragment -> {
                val action = NavGraphDirections.actionGlobalLoginFragment()
                navController.navigate(action)
                true
            }
            R.id.searchFragment -> {
                val searchView = item.actionView as SearchView


                searchView.setOnQueryTextListener(object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        Log.i("well", " Last: $query")
                        val action = NavGraphDirections.actionGlobalSearchFragment(query)
                        navController.navigate(action)
                        return true

                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        Log.i("well", " this worked : $newText")
                        return true
                    }
                })
                true
            }
            else -> {
                item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

            }
        }


    }

}
