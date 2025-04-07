JUST EAT CODING ASSESSMENT

This Android Application was developed as part of the JustEat Coding Challenge. The objective of this application
is to fetch restaurant data from an API and display a list of restaurants and it's key details.

The app is built with Jetpack Compose for UI, following the MVVM Architectural pattern,
it also utilizes Retrofit for network calls and Moshi for JSON serialization.

📝 Project Overview and Features
- Fetches a list of 10 restaurants via an API call
- Displays Name,Cuisine,Rating and Address
- Expandable list items
- Search via postcode
- Built with Jetpack Compose
- Follows MVVM

⛏️ Dependencies and build
This project uses Gradle for dependency managment, and the following key dependencies:
- Jetpack Compose for building the UI
- Retrofit and Moshi for API calls and data parsing
- LiveData and ViewModel for lifecycle aware state management

🧑‍💻  How to build, compile and run
- Clone the repository
- Open in Android Studio
- Sync gradle
- Run the app (via emulator or by connecting to an android device)

✍️ Assumptions and Improvements
In terms of assumptions or things that were made unclear there was only one thing that required clarification,
when it came time to displaying the types of cuisine the endpoint returned a lot of terms such as "Halal" or "Low Delivery fee",
these were clarified for me upon communicating with a member from the justeat team.

In terms of improvements:
- UI enhancements: adding animations to the composables when they expand to seem more dynamic for a better experience. Also implementing a theme that echoes the colour scheme of the Just Eat application
- Testing: add unit tests for the logic in the ViewModel as well as implementing Compose UI tests
- Caching to minimize API calls
- Creating a Constants.kt file to store the excluded keywords to make the code more maintainable
