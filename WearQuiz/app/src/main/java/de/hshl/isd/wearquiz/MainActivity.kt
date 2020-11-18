package de.hshl.isd.wearquiz

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.widget.drawer.WearableNavigationDrawerView

class MainActivity : FragmentActivity(),
    AmbientModeSupport.AmbientCallbackProvider,
    WearableNavigationDrawerView.OnItemSelectedListener {

    private lateinit var ambientController: AmbientModeSupport.AmbientController
    private lateinit var wearableNavigationDrawer: WearableNavigationDrawerView

    val fragments = listOf<NavigationDrawerFragment>(
        MainFragment.newInstance(), StatisticsFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ambientController = AmbientModeSupport.attach(this)

        wearableNavigationDrawer = findViewById(R.id.top_navigation_drawer)
        wearableNavigationDrawer.setAdapter(object :
            WearableNavigationDrawerView.WearableNavigationDrawerAdapter() {

            override fun getItemText(pos: Int): CharSequence = fragments[pos].navDrawerText

            override fun getItemDrawable(pos: Int): Drawable = resources.getDrawable(
                fragments[pos].navDrawerDrawable,
                theme
            )

            override fun getCount(): Int = fragments.size

        })
        wearableNavigationDrawer.controller.peekDrawer()
        wearableNavigationDrawer.addOnItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragments[0] as Fragment)
                .commitNow()
        }
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback =
        object : AmbientModeSupport.AmbientCallback() {}

    override fun onItemSelected(pos: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, this.fragments[pos] as Fragment)
            .commitNow()
    }

}