package com.example.capitertask.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.capitertask.R
import com.example.capitertask.databinding.ViewProductBinding

class ProductView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val _binding: ViewProductBinding =
        ViewProductBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ProductView,
            0, 0
        ).apply {
            try {
                val type = getInteger(R.styleable.ProductView_type, 0)
                when (type) {
                    0 -> {
                        _binding.addToCartButton.visibility = GONE
                        _binding.deleteFromCartImageView.visibility = GONE
                    }
                    1 -> {
                        _binding.addToCartButton.visibility = VISIBLE
                        _binding.deleteFromCartImageView.visibility = GONE
                    }
                    2 -> {
                        _binding.addToCartButton.visibility = GONE
                        _binding.deleteFromCartImageView.visibility = VISIBLE
                    }
                }
            } finally {
                recycle()
            }
        }
    }
}

