package com.example.capitertask.presentation

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capitertask.databinding.ActivityDealerMainBinding
import com.example.capitertask.domain.models.NamedOrder
import com.example.capitertask.presentation.base.BaseActivity
import com.example.capitertask.presentation.utils.SingleEvent
import com.example.capitertask.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealerMainActivity : BaseActivity() {
    private val _viewModel: DealerViewModel by viewModels()
    lateinit var dealerBinding: ActivityDealerMainBinding
    private val _adapter = NamedOrdersAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dealerBinding = ActivityDealerMainBinding.inflate(layoutInflater)
        setContentView(dealerBinding.root)
        initViews()
        obsereveLiveData()
        _viewModel.getOrders()
    }

    private fun obsereveLiveData() {
        observe(_viewModel.toastLiveData, ::handleToast)
        observe(_viewModel.namedOrdersLiveData, ::handleNamedOrders)
        observe(_viewModel.showProgressBarLiveData,::handleProgressBar)
    }

    private fun handleProgressBar(singleEvent: SingleEvent<Boolean>) {
        if(singleEvent.peekContent())
            dealerBinding.loadingProgressBar.visibility = View.VISIBLE
        else
            dealerBinding.loadingProgressBar.visibility = View.GONE

    }

    private fun handleNamedOrders(list: List<NamedOrder>) {
        _adapter.setNamedOrders(list)
    }

    private fun handleToast(singleEvent: SingleEvent<String>) {
        showToast(singleEvent.peekContent())
    }

    private fun initViews() {
        dealerBinding.namedOrdersRecyclerView.adapter = _adapter
        dealerBinding.namedOrdersRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}