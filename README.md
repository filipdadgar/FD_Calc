# FD_Calc

FD_Calc is a modern, minimal calculator app for Android, built as a sample project to demonstrate best practices with Jetpack libraries and the MVVM architecture.

## Features
- Basic arithmetic operations: addition, subtraction, multiplication, division
- Supports decimal numbers and full input expression display
- Clean, accessible UI with Material Components
- Responsive layout with support for system navigation insets
- Footer label with version and author information

## Tech Stack
- **Language:** Kotlin
- **Minimum SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Libraries:**
  - Jetpack ViewModel & LiveData
  - Jetpack Navigation Component
  - ViewBinding
  - Material Components for Android

## Project Structure
- `MainActivity.kt`: Hosts the navigation and bottom navigation bar
- `ui/calculator/CalculatorFragment.kt`: Main calculator UI and logic
- `ui/calculator/CalculatorViewModel.kt`: Handles calculator state and operations
- `res/layout/fragment_calculator.xml`: Calculator UI layout
- `res/values/strings.xml`: All UI strings for localization
- `res/navigation/mobile_navigation.xml`: Navigation graph

## Build & Run
1. Open the project in Android Studio (Giraffe or newer recommended)
2. Build with Gradle wrapper (`./gradlew build`)
3. Run on an emulator or device (`./gradlew installDebug` or via Android Studio)

## Customization
- To add new features, create new Fragments/ViewModels and update the navigation graph as needed.
- All UI strings are in `strings.xml` for easy localization.

## License
MIT License

---

**Author:** Filip Dadgar
**Version:** 1.0
