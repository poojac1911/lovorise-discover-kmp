# Lovorise - Discover Module

A Kotlin Multiplatform (KMP) implementation of the Discover module for the Lovorise app. This project focuses on a clean, responsive, and interactive mobile UI using Compose Multiplatform.

## 🚀 Key Features

-   **Interleaved Feed**: Stories and Profile cards interleaved in a single infinite-scrolling feed.
-   **Stories Section**: 
    -   Supports single and multiple images.
    -   Smooth horizontal paging for multi-image stories with pagination indicators (e.g., 2/6).
    -   Action sidebar with Like, Comment, Gift, and Share counters.
-   **Profile Cards**: 
    -   Detailed profile information (Name, Age, Location, Bio).
    -   Direct interactions: "Add Connection" and "Message".
    -   Status indicators (Online).
-   **Responsive Navigation**: 
    -   Top tabs for "For You" and "Connections".
    -   Bottom navigation bar with custom styling.
-   **Animations & UX**:
    -   Smooth scrolling.
    -   Gradient overlays for text readability over images.
    -   Infinite scroll simulation.

## 🛠 Technical Stack

-   **Framework**: Kotlin Multiplatform (KMP)
-   **UI framework**: Compose Multiplatform
-   **Image Loading**: Coil 3 (KMP)
-   **Network**: Ktor (for Coil image loading)
-   **Architecture**: Component-based architecture with clean folder structure.
-   **Mock Data**: JSON-like Kotlin models and mock repositories.

## 📂 Folder Structure

```text
composeApp/src/commonMain/kotlin/com/example/lovorise/
├── data/
│   ├── model/         # Data classes for Feed, Story, Profile
│   └── mock/          # Mock data provider
├── ui/
│   ├── components/    # Reusable generic components
│   ├── discover/      # Discover module specific screens and widgets
│   └── theme/         # Design system (Colors, Typography, Theme)
└── App.kt             # Main entry point
```

## 🛠 Setup & Installation

1.  **Clone the Repository**:
    ```bash
    git https://github.com/poojac1911/lovorise-discover-kmp.git
    cd lovorise-discover-kmp
    ```

2.  **Open in Android Studio / IntelliJ IDEA**:
    -   Ensure you have the **Kotlin Multiplatform** plugin installed.
    -   Open the root `build.gradle.kts`.

3.  **Run the App**:
    -   **Android**: Select `composeApp` configuration and run on an emulator/device.
    -   **iOS**: Run `iosApp` from Xcode or using the KMP plugin.