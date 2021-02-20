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
                val name = getString(R.styleable.ProductView_name)
                _binding.nameTextView.text = name
                val amount = getInteger(R.styleable.ProductView_amount, 0)
                _binding.countTextView.text = amount.toString()
                val price = getFloat(R.styleable.ProductView_price, -1f)
                if (price > 0) {
                    _binding.priceTextView.apply {
                        visibility = VISIBLE
                        text = price.toString()
                    }

                } else {
                    _binding.priceTextView.visibility = INVISIBLE
                }
                val srcDrawable = getResourceId(R.styleable.ProductView_src, -1)
                if (srcDrawable > -1) {
                    _binding.productImageView.apply {
                        visibility = VISIBLE
                        setImageResource(srcDrawable)
                    }
                } else {
                }
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

