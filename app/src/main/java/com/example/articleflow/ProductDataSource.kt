package com.example.articleflow

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class ProductDataSource(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }

    fun addProduct(product: Product) {
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME, product.name)
            put(DatabaseHelper.COLUMN_PRICE, product.price)
            put(DatabaseHelper.COLUMN_QUANTITY, product.quantity)
        }
        database.insert(DatabaseHelper.TABLE_PRODUCTS, null, values)
    }

    fun deleteProduct(id: Long) {
        database.delete(DatabaseHelper.TABLE_PRODUCTS, "${DatabaseHelper.COLUMN_ID} = ?", arrayOf(id.toString()))
    }

    fun getAllProducts(): List<Product> {
        val products = mutableListOf<Product>()
        val cursor: Cursor = database.query(DatabaseHelper.TABLE_PRODUCTS, null, null, null, null, null, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val product = cursorToProduct(cursor)
            products.add(product)
            cursor.moveToNext()
        }
        cursor.close()
        return products
    }

    private fun cursorToProduct(cursor: Cursor): Product {
        return Product(
            id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)),
            price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRICE)),
            quantity = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUANTITY))
        )
    }
}