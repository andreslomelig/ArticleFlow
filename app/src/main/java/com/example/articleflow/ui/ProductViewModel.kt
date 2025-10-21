package com.example.articleflow.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.articleflow.data.AppDatabase
import com.example.articleflow.data.Product
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {
    private val dao = AppDatabase.get(application).productDao()
    val products = dao.getAll()

    fun addProduct(name: String, price: Double, quantity: Int) {
        viewModelScope.launch {
            dao.insert(Product(name = name, price = price, quantity = quantity))
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            dao.delete(product)
        }
    }
}
