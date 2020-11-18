package de.hshl.isd.wearquiz

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.widget.drawer.WearableNavigationDrawerView

class MainActivity : FragmentActivity(),
    AmbientModeSupport.AmbientCallbackProvider,
    WearableNavigationDrawerView.OnItemSelectedListener {

    private lateinit var ambientController: AmbientModeSupport.AmbientController
    private lateinit var wearableNavigationDrawer: WearableNavigationDrawerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ambientController = AmbientModeSupport.attach(this)

        wearableNavigationDrawer = findViewById(R.id.top_navigation_drawer)
        wearableNavigationDrawer.setAdapter(object :
            WearableNavigationDrawerView.WearableNavigationDrawerAdapter() {
            val fragments = listOf(
                Pair("Text", R.drawable.ic_baseline_thumb_down_24),
                Pair("Text", R.drawable.ic_baseline_thumb_down_24)
            )

            override fun getItemText(pos: Int): CharSequence = fragments[pos].first

            override fun getItemDrawable(pos: Int): Drawable = resources.getDrawable(
                fragments[pos].second,
                theme
            )

            override fun getCount(): Int = fragments.size

        })
        wearableNavigationDrawer.controller.peekDrawer()
        wearableNavigationDrawer.addOnItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback =
        object : AmbientModeSupport.AmbientCallback() {}

    override fun onItemSelected(pos: Int) {
        Log.d("MainActivity", pos.toString())
    }

}