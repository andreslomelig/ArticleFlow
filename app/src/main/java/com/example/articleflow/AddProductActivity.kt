package com.example.articleflow

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.articleflow.databinding.ActivityAddProductBinding
import com.example.articleflow.ui.ProductViewModel

class AddProductActivity : AppCompatActivity() {

    private lateinit var b: ActivityAddProductBinding
    private val vm: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnSave.setOnClickListener {
            val name = b.etName.text?.toString()?.trim().orEmpty()
            val priceStr = b.etPrice.text?.toString()?.trim().orEmpty()
            val qtyStr = b.etQuantity.text?.toString()?.trim().orEmpty()

            if (name.isEmpty() || priceStr.isEmpty() || qtyStr.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceStr.toDoubleOrNull()
            val qty = qtyStr.toIntOrNull()

            if (price == null || qty == null || price < 0 || qty < 0) {
                Toast.makeText(this, "Valores inválidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            vm.addProduct(name, price, qty)
            Toast.makeText(this, "Producto guardado", Toast.LENGTH_SHORT).show()
            finish() // vuelve a MainActivity
        }
    }
}
