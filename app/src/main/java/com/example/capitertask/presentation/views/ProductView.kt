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
    val binding: ViewProductBinding =
        ViewProductBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ProductView,
            0, 0
        ).apply {
            try {
                val name = getString(R.styleable.ProductView_name)
                binding.nameTextView.text = name
                val amount = getInteger(R.styleable.ProductView_amount, 0)
                binding.countTextView.text = amount.toString()
                val price = getFloat(R.styleable.ProductView_price, -1f)

                binding.priceTextView.apply {
                    visibility = VISIBLE
                    text = price.toString()
                }


                val srcDrawable = getResourceId(R.styleable.ProductView_src, -1)
                if (srcDrawable > -1) {
                    binding.productImageView.apply {
                        visibility = VISIBLE
                        setImageResource(srcDrawable)
                    }
                } else {
                }
                val type = getInteger(R.styleable.ProductView_type, 0)
                when (type) {
                    0 -> {
                        binding.addToCartButton.visibility = GONE
                        binding.deleteFromCartImageView.visibility = GONE
                    }
                    1 -> {
                        binding.addToCartButton.visibility = VISIBLE
                        binding.deleteFromCartImageView.visibility = GONE
                    }
                    2 -> {
                        binding.addToCartButton.visibility = GONE
                        binding.deleteFromCartImageView.visibility = VISIBLE
                    }
                }
            } finally {
                recycle()
            }
        }
    }
}

