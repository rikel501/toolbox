package com.toolbox.ui.carousels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.toolbox.app.di.DaggerAppComponent
import com.toolbox.databinding.FragmentCarouselsBinding
import com.toolbox.ui.carousels.adapter.CarouselsAdapter
import com.toolbox.ui.carousels.mvvm.CarouselsRepository
import com.toolbox.ui.carousels.mvvm.CarouselsVM
import com.toolbox.ui.carousels.mvvm.CarouselsVMF
import javax.inject.Inject

class CarouselsFragment : Fragment() {

    @Inject
    lateinit var repository: CarouselsRepository

    private lateinit var binding: FragmentCarouselsBinding
    private lateinit var viewModel: CarouselsVM
    private lateinit var adapter: CarouselsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarouselsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.create().inject(this)
        initRV()
        initVM()
    }

    fun initRV() {
        binding.rvCarousels.layoutManager = LinearLayoutManager(context)
        adapter = CarouselsAdapter()
        binding.rvCarousels.adapter = adapter
    }

    fun initVM() {
        val factory = CarouselsVMF(repository)
        viewModel = ViewModelProvider(this, factory).get(CarouselsVM::class.java)
        binding.lifecycleOwner = this
        viewModel.getCarousels()
        initObservers()
    }

    fun initObservers() {
        viewModel.carousels.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            adapter.setMoreData(it)
        })
    }

}