# ArticleFlow

**ArticleFlow** is a simple Android app built with **Kotlin** that allows users to **manage products** of any type —  
add new products, list existing ones, and delete them from a local database.

The project leverages **Room (SQLite)** for persistence, **RecyclerView** for dynamic lists, and **ViewModel + LiveData** to keep the UI automatically synchronized with the database.

---

## Current Features

- **View all products** stored locally  
- **Add new product** (name, price, quantity)  
- **Delete product** with confirmation dialog  
- **Persistent local storage** using Room (SQLite)  
- **Auto-updating UI** with LiveData  
- **Material Design** interface for a clean, modern look

---

## Tech Stack

| Component | Description |
|------------|-------------|
| **Kotlin** | Main programming language |
| **Android Jetpack** | Architecture components (ViewModel, LiveData, Room) |
| **Room** | SQLite abstraction layer for persistence |
| **RecyclerView** | Displays the dynamic list of products |
| **Material Components** | Implements modern Android design |
| **ViewBinding** | Type-safe view access (no `findViewById`) |
| **Activity KTX** | Provides `by viewModels()` delegate for ViewModel management |

---

## Project Structure

```
app/
├── java/com/example/articleflow/
│ ├── MainActivity.kt → Main screen: product list
│ ├── AddProductActivity.kt → Add product form
│ ├── data/
│ │ ├── Product.kt → Room entity
│ │ ├── ProductDao.kt → Data access object (DAO)
│ │ └── AppDatabase.kt → Room database configuration
│ └── ui/
│ ├── ProductAdapter.kt → RecyclerView adapter
│ └── ProductViewModel.kt → ViewModel managing data logic
└── res/layout/
├── activity_main.xml
├── activity_add_product.xml
└── item_product.xml
```

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/andreslomelig/ArticleFlow.git
   ```
2. Open the project in Android Studio.
3. Wait for Gradle synchronization to complete.
4. Run the app on an emulator or a physical Android device (API 24+ / Android 7.0+).

## Architecture
The app follows the MVVM (Model - View - ViewModel) architecture pattern:
- Model: Product, ProductDao, AppDatabase
- View: XML layouts and Activities (MainActivity, AddProductActivity)
- ViewModel: ProductViewModel connects the UI with the database using Kotlin coroutines and LiveData.




