package com.example.capitertask.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.example.capitertask.databinding.ViewProductBinding

class ProductView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    init {
        initViews()
    }

    private fun initViews() {
        val binding =
            ViewProductBinding.inflate(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)


    }

}