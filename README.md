# Gallery 🎨 

'Gallery' is an Android application that allows users to view a list of images, save their favorite images, and search for images based on keywords. The application is written in **Kotlin** and uses **Jetpack Compose** for the UI. It follows the principles of **clean architecture**.

## Demo 🎥

<img src="https://raw.githubusercontent.com/MahdiGharooni/galleryKT/master/assets/gallery1.JPEG" alt="1" height="400">  <img src="https://raw.githubusercontent.com/MahdiGharooni/galleryKT/master/assets/gallery2.JPEG" alt="2" height="400">  <img src="https://raw.githubusercontent.com/MahdiGharooni/galleryKT/master/assets/gallery3.JPEG" alt="3" height="400">  



## Features 📄

- **Image List**: The application sends a request to the server to receive a list of images with pagination. This is done using Retrofit2.
- **Offline Support**: Images are saved in a local database per page using Room. If the user is offline, the application loads images from the database.
- **Favorites**: Users can mark images as favorites.
- **Search**: The application includes a search section. Users can search for a keyword and receive a list of images from the server related to the keyword.

## Libraries 📚 

- **Kotlin**: The project is entirely written in Kotlin.
- **Jetpack Compose**: The new UI toolkit from Google to design Android apps.
- **Retrofit2**: A type-safe HTTP client for Android and Java.
- **Room**: Provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
- **Hilt**: Dependency Injection.

## Architecture 🏗️ 

The application follows the principles of clean architecture, which are intended to make the architecture:

- Independent of Frameworks
- Testable
- Independent of UI
- Independent of Database
- Independent of any external agency

## How to Run 🚀 

1. Clone the repository
2. Open the project in Android Studio
3. Run the application on an emulator or an actual device

## Contributions 🤝

Contributions are welcome. Please open a pull request with your changes.

