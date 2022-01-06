package com.saifkhan.completeappdemo.Ui.activity

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saifkhan.productdbapp.MainRepository
import com.saifkhan.productdbapp.R
import com.saifkhan.productdbapp.Resource
import com.saifkhan.productdbapp.Utils.hasInternetConnection
import com.saifkhan.productdbapp.data.models.ProductModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class HomeViewModel(private val appRepository: MainRepository,private val context:Context) : ViewModel() {

    val prodctsData: MutableLiveData<Resource<List<ProductModel>>> = MutableLiveData()

    init {
        getProducts()
    }

    fun getProducts() = viewModelScope.launch {
        fetchProducts()
    }


    private suspend fun fetchProducts() {

        prodctsData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(context)) {
                val res = appRepository.getAllProducts()
                prodctsData.postValue(handleProductResponse(res))
            }else{
                prodctsData.postValue(Resource.Error(R.string.no_internet_connection.toString()))
            }
        }catch (t:Throwable){
            when(t){
                is IOException -> prodctsData.postValue(Resource.Error(R.string.network_failure.toString()))
            }
        }
    }

    private fun handleProductResponse(response: Response<List<ProductModel>>):  Resource<List<ProductModel>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }else{
            response.body().let {
                return Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }


}