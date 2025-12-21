# Movie Hub

A modern Android application showcasing Studio Ghibli films, built with Clean Architecture, MVI (Model-View-Intent) pattern, and Jetpack Compose.

## 📝 Description

Movie Hub is an Android application demonstrating modern development practices. The project follows Clean Architecture principles and implements MVI architecture for predictable state management. Built entirely with Jetpack Compose and Material 3, featuring a beautiful UI to browse and explore Studio Ghibli films.

## 📱 Screenshots

![Splash Screen](screenshots/splash_screen.png)
![Movies List](screenshots/movies_list.png)
![Movie Detail](screenshots/movie_detail.png)

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
