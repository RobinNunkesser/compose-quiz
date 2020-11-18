package de.hshl.isd.wearquiz

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport

class MainActivity : FragmentActivity(),
        AmbientModeSupport.AmbientCallbackProvider {

    private lateinit var ambientController: AmbientModeSupport.AmbientController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ambientController = AmbientModeSupport.attach(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback =
            object : AmbientModeSupport.AmbientCallback() {}

}