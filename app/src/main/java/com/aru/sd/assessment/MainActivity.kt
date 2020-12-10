package com.aru.sd.assessment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.aru.sd.assessment.fragments.HomeFragment
import com.aru.sd.assessment.fragments.OrderFragment
import com.aru.sd.assessment.fragments.ProductFragment
import com.aru.sd.assessment.fragments.StoreFragment
import rebus.permissionutils.PermissionEnum
import rebus.permissionutils.PermissionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showLocationPermission()

        storeBtn.setOnClickListener(View.OnClickListener {
            resetButtons()
            storeBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            storeBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_store_selected, 0,0)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StoreFragment())
                .addToBackStack(null)
                .commit()
        })

        customerBtn.setOnClickListener(View.OnClickListener {
            resetButtons()
            customerBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            customerBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_selected, 0,0)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .addToBackStack(null)
                .commit()
        })

        productBtn.setOnClickListener(View.OnClickListener {
            resetButtons()
            productBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            productBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_card_selected, 0,0)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductFragment())
                .addToBackStack(null)
                .commit()
        })

        ordersBtn.setOnClickListener(View.OnClickListener {
            resetButtons()
            ordersBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            ordersBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_order_selected, 0,0)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OrderFragment())
                .addToBackStack(null)
                .commit()
        })


        resetButtons()
        customerBtn.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        customerBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_selected, 0, 0)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun resetButtons() {
        ordersBtn.setTextColor(ContextCompat.getColor(this, R.color.tab_color_unselected))
        storeBtn.setTextColor(ContextCompat.getColor(this, R.color.tab_color_unselected))
        productBtn.setTextColor(ContextCompat.getColor(this, R.color.tab_color_unselected))
        customerBtn.setTextColor(ContextCompat.getColor(this, R.color.tab_color_unselected))

        ordersBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_order, 0,0)
        storeBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_store, 0,0)
        productBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_card, 0,0)
        customerBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home, 0,0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handleResult(this, requestCode, permissions, grantResults)
    }

    private fun showLocationPermission()
    {
        val permissions = ArrayList<PermissionEnum>()
        permissions.add(PermissionEnum.ACCESS_COARSE_LOCATION)
        permissions.add(PermissionEnum.ACCESS_FINE_LOCATION)

        PermissionManager.Builder()
            .permissions(permissions)
            .askAgain(true)
            .askAgainCallback { response ->

                AlertDialog.Builder(this@MainActivity)
                    .setTitle(getString(R.string.app_name))
                    .setMessage(getString(R.string.permission_required_text))
                    .setPositiveButton(android.R.string.ok,
                        DialogInterface.OnClickListener { dialogInterface, _ -> response.result(true) })
                    .setNegativeButton(getString(R.string.not_now),
                        DialogInterface.OnClickListener { dialogInterface, _ -> response.result(false) })
                    .show()
            }
            .callback { permissionsGranted, permissionsDenied, permissionsDeniedForever, permissionsAsked ->
                if (permissionsGranted.size == permissions.size) {
                    app.addLocationUpdates()
                }
            }
            .ask(this@MainActivity)
    }
}