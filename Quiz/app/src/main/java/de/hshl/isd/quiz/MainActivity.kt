package de.hshl.isd.quiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.action_statistics -> findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_mainFragment_to_statisticsFragment)
        }


        return super.onOptionsItemSelected(item)
    }

}
