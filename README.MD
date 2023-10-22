# Notion API + Ktor Test App with Compose Multiplatform

## Before running
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there
 - add plugin `Kotlin Multiplatform Mobile` to Android Studio
 - (optional) add plugin `Compose Multiplatform IDE Support` to Android Studio
 - place Notion API Key to `local.properties` file as `notion.api.key` property
 - place Notion Database ID to `local.properties` file as `notion.db.id` property, 
   this value can be found in the URL of your database(32 characters long string)

## Blog
For detail information, please read [this article](https://zenn.dev/articles/c2ce9e1a97159).

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`

### Desktop
Run the desktop application: `./gradlew :composeApp:run`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

