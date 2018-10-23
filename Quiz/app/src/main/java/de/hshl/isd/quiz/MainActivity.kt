package de.hshl.isd.quiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.action_statistics -> supportFragmentManager.beginTransaction()
                    .replace(R.id.container, StatisticsFragment.newInstance())
                    .commitNow()
        }


        return super.onOptionsItemSelected(item)
    }

}
