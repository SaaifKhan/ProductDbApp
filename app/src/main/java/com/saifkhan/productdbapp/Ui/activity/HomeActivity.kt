package com.saifkhan.completeappdemo.Ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.saifkhan.completeappdemo.Ui.activity.adapter.ProductIstAdapter
import com.saifkhan.productdbapp.MainRepository
import com.saifkhan.productdbapp.R
import com.saifkhan.productdbapp.Resource
import com.saifkhan.productdbapp.data.models.ProductModel
import com.saifkhan.productdbapp.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding


    val productListAdapter by lazy {
        ProductIstAdapter(object : ProductIstAdapter.OnItemClickListener {
            override fun onItemClick(itemPosition: Int, productModel: ProductModel) {


            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this

        init()

    }

    private fun init() {
        productList.adapter = productListAdapter
        setupViewModel()

    }

    private fun setupViewModel() {
        val repository = MainRepository()
        val factory = HomeViewModelFactory(repository,this)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        getProducts()
    }

    private fun getProducts() {
        viewModel.prodctsData.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data.let {
                        hideProgressBar()
                        if (it?.isNotEmpty()!!) {
                            productListAdapter.submitList(it)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let { message ->
                   //    rootLayout.errorSnack(message, Snackbar.LENGTH_LONG)
                    }

                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun hideProgressBar() {
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }


    fun onProgressClick(view: View) {
        //Preventing Click during loading
    }

}
