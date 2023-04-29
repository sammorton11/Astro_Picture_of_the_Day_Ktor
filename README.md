# Astro_Picture_of_the_Day_Ktor

NASA APOD Viewer
NASA APOD Viewer is an Android app that allows you to view the Astronomy Picture of the Day (APOD) provided by NASA's APOD API. The app uses the Model-View-ViewModel (MVVM) architecture, coroutines for asynchronous operations, Ktor for networking, Koin for dependency injection, Room for local database storage, and Jetpack Compose for UI.

Features
The app has the following features:

View the APOD for the current date on the home screen
Select a past date on the date selection screen to view the APOD for that date
Favorite an APOD by tapping the favorite button on the home screen or the detail screen
View a list of favorite APODs on the favorites screen
Installation
To install the app, follow these steps:

Clone the repository to your local machine.
Open the project in Android Studio.
Build the project to generate the APK.
Install the APK on your Android device or emulator.
Usage
To use the app, follow these steps:

Open the app on your device or emulator.
The home screen will display the APOD for the current date.
To view the APOD for a past date, tap the calendar icon on the home screen to go to the date selection screen.
Select a date by tapping on the calendar, and then tap the "View APOD" button to view the APOD for that date.
To favorite an APOD, tap the favorite button on the home screen or the detail screen. The APOD will be added to the favorites list.
To view the favorites list, tap the favorites icon on the home screen. The favorites screen will display a list of all the APODs that have been favorited.
Technical Details
The app uses the following technologies:

MVVM architecture for separation of concerns and easy testing
Coroutines for asynchronous operations such as network requests and database operations
Ktor for networking with the NASA APOD API
Koin for dependency injection to simplify the setup of the app
Room for local database storage of favorite APODs
Jetpack Compose for building the UI
