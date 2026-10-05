# Product Catalog

An Android product catalog app built using Kotlin and Jetpack Compose.

The app allows users to browse products, search for products, view product details, and manage a cart that works offline.

## Features

- Product listing from REST API
- Product search
- Product details
- Add products to cart
- Increase/decrease product quantity
- Delete products from cart
- Cart total item count
- Cart total price
- Offline cart using Room
- Loading, empty and error states
- Retry when product API fails
- Dependency injection using Hilt
- MVVM architecture

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM
- ViewModel
- StateFlow
- Hilt
- Retrofit
- Gson
- OkHttp
- Room
- Coil
- Navigation Compose

## API

The app uses the [DummyJSON](https://dummyjson.com/) API for product data.

Main endpoints used:

- `GET /products`
- `GET /products/search?q={query}`
- `GET /products/{id}`

The products API supports `limit` and `skip`, which can be used for pagination.

## Architecture

The project follows a simple MVVM-based structure.

```text
com.productcatalog.app
│
├── data
│   ├── retrofit
│   └── room
│
├── features
│   ├── product
│   │   ├── model
│   │   ├── repo
│   │   ├── viewmodel
│   │   └── screens
│   │       └── productDetail
    │       └── productListing
│   │
│   └── cart
│       ├── model
│       ├── repo
│       ├── viewModel
│       └── screens
│
├── hilt
│
└── ui
    └── theme
```

## Error Handling
The product API handles common network failures such as:

- No internet connection
- Request timeout
- General API failures

The UI displays an appropriate error message and provides a retry option.

## Testing
I tested the main application flows, including:
- Loading products
- Searching products
- Opening product details
- Adding products to cart
- Changing cart quantities
- Deleting cart items
- Calculating cart totals
- Handling API/network errors
- Retry functionality
- Empty states
- Offline cart persistence

## Local Storage
Room is used for local cart storage.
When a product is added to the cart, the required product information and quantity are stored in the local Room database. Cart operations such as increasing/decreasing quantity and deleting items are performed locally.
This allows the cart to work without an internet connection.

## Important Design Decisions
- Used MVVM to separate UI, state management and data access.
- Used repositories to keep API and database operations separate from the UI.
- Used Hilt for dependency injection.
- Used Retrofit for REST API communication.
- Used Room for offline cart persistence.
- Used StateFlow for exposing UI state from ViewModels.
- Used Jetpack Compose for building the UI.
- Kept the cart local because cart operations do not require network access.
- Prioritized completing the core functionality before spending time on UI refinement.

## Build
Clone the repository and open it in Android Studio.
Then build and run the application on an emulator or physical Android device.


## Requirements
- Android Studio
- JDK compatible with the project
- Android SDK
- Internet connection for loading products from the API

## Future Improvements

- Complete pagination for the home screen using the API's `skip` and `limit` parameters
- Further UI refinement
- Add additional features planned for the application
