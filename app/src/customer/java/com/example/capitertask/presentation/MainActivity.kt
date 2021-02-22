package com.example.capitertask.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.capitertask.R
import com.example.capitertask.databinding.ActivityMainBinding
import com.example.capitertask.presentation.base.BaseActivity
import com.example.capitertask.presentation.cart.CartFragment
import com.example.capitertask.presentation.products.ProductsFragment
import com.example.capitertask.presentation.products.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: ProductsViewModel by viewModels()
    private var _menu: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val productsFragment = ProductsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, productsFragment, "PRODUCTS_TAG")
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_customer, menu)
        menu?.let { _menu = menu }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_cart -> {
            val cartFragment = CartFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.container, cartFragment, "CARTS_TAG")
                .addToBackStack("CARTS_TAG").commit()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun showCartIcon(shouldShow: Boolean) {

        if (_menu != null)
            _menu?.findItem(R.id.action_cart)?.setVisible(shouldShow)
    }
}