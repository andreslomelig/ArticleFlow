package com.example.articleflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articleflow.databinding.ActivityMainBinding
import com.example.articleflow.ui.ProductAdapter
import com.example.articleflow.ui.ProductViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private val vm: ProductViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        adapter = ProductAdapter(onDelete = { product ->
            AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("¿Seguro que deseas eliminar \"${product.name}\"?")
                .setPositiveButton("Sí") { _, _ -> vm.deleteProduct(product) }
                .setNegativeButton("Cancelar", null)
                .show()
        })

        b.rvProducts.layoutManager = LinearLayoutManager(this)
        b.rvProducts.adapter = adapter

        vm.products.observe(this) { list ->
            adapter.submitList(list)
        }

        b.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }
    }
}
