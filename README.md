# Movie Hub

A modern Android application showcasing Studio Ghibli films, built with Clean Architecture, MVI (Model-View-Intent) pattern, and Jetpack Compose.

## 📝 Description

Movie Hub is an Android application demonstrating modern development practices. The project follows Clean Architecture principles and implements MVI architecture for predictable state management. Built entirely with Jetpack Compose and Material 3, featuring a beautiful UI to browse and explore Studio Ghibli films.

The app flow starts with a splash screen showing the app icon and animated progress bar. After the splash screen, users are presented with the Movie Hub screen displaying a list of all Studio Ghibli films with thumbnails, descriptions, and star ratings. Tapping on any movie opens a detailed view showing comprehensive information including the movie poster, director, producer, release date, description, and rating.


## 📱 Screenshots

<img width="200" alt="splash" src="https://github.com/user-attachments/assets/ac30f8b8-d47c-48fa-8eb1-6aecc2f7860d"  />
<img width="200" alt="All movies" src="https://github.com/user-attachments/assets/b4b939e8-80df-49ce-b3fa-49b61752c26e" />
<img width="200" alt="movie detail 3" src="https://github.com/user-attachments/assets/d10f1a74-0c1b-4a4f-982f-325ab6d4d760" />
<img width="200" alt="movie detail 2" src="https://github.com/user-attachments/assets/60fe9d23-d3b3-4add-abf4-2fbb517a3232" />

## ✨ Features

- Browse Studio Ghibli films collection
- View detailed movie information
- Star rating system
- Smooth navigation between screens
- Edge-to-edge UI design
- Splash screen with animated progress

## 🏗️ Architecture

### Clean Architecture

- **Presentation Layer**: Feature-based UI (Intent, State, ViewModel, View)
- **Domain Layer**: Business logic, Models, Repository interfaces
- **Data Layer**: Repository implementations, API services, DTOs

### MVI Pattern

- **Intent**: Sealed classes for user actions
- **State**: Immutable UI state
- **ViewModel**: Processes intents, manages state
- **View**: Observes state, sends intents
- **Data Flow**: View → Intent → ViewModel → State → View

## 🛠️ Tech Stack

- **UI**: Jetpack Compose with Material 3
- **Architecture**: Clean Architecture + MVI
- **Dependency Injection**: Hilt
- **Navigation**: Navigation Compose
- **Networking**: Retrofit + OkHttp
- **Image Loading**: Coil
- **Language**: Kotlin
- **Async**: Kotlin Coroutines & Flow

## 📁 Project Structure

```
Movies App/
├── app/                    # Application module
├── data/                   # Data Layer
│   ├── remote/            # API services, DTOs
│   └── repository/         # Repository implementations
├── domain/                # Domain Layer
│   ├── model/             # Domain models
│   └── repository/        # Repository interfaces
└── presentation/          # Presentation Layer
    ├── movies_list/       # Movies List Feature
    ├── movie_detail/      # Movie Detail Feature
    ├── splash_screen/     # Splash Screen
    └── shared/            # Shared UI resources
```

## 🚀 Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Build and run

## 🌐 API

Uses [Studio Ghibli API](https://ghibliapi.vercel.app/) - No API key required.

---

**Made with ❤️ using Kotlin and Jetpack Compose**
