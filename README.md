# Currency Budy

Currency Budy is a simple and user-friendly currency converter app built with Android and Jetpack Compose that allows you to quickly and accurately convert currencies on-the-go.

## Features

- Simple and intuitive UI for easy currency conversion
- Reliable exchange rate data sources for accurate conversions
- Available offline, with no need for an internet connection
- Designed to be used on-the-go, for convenient currency conversion anytime, anywhere

## Usage

1. Fork the project and clone it to your computer.
2. Import the project into Android Studio and run it on an Android emulator or physical device.
3. Launch Currency Budy and select the currency you want to convert from and the currency you want to convert to.
4. Enter the amount you want to convert, and Currency Budy will instantly display the converted amount.
5. Repeat the process for any other currency conversions you need.

## Prerequisites

remember to add an apikey.properties file in the root package that has the following:
- API_KEY = "YOUR_API_KEY"
- The app uses exchangerate-api, here is the link https://www.exchangerate-api.com/
- You can get your free API key which has a 1500 requests per month quota. 

## Development

Currency Budy is built using Android and Jetpack Compose, and utilizes the following dependencies:
- Hilt for dependency injection 
- Room database for caching 
- Coroutines for asynchronous operations
- Pager for onboarding
- Navigation Component for navigation
- Retrofit for network operations 
- Splash Screen API for the splash screen
- Preference DataStore for saving onboarding state 

If you're interested in contributing to the development of Currency Budy, please reach out.

## Support

If you encounter any issues while using Currency Budy, or if you have any questions or feedback, please [contact me](mailto:ericwathome007@gmail.com) or open an issue on this repository.

### Architecture

The application uses clean architecture and makes use of features i.e feature_onboarding & feature_converter. Each feature follows the common convention as shown below

![image](https://user-images.githubusercontent.com/82439687/222801500-0d3bcbd7-49e3-4b62-bcdb-94ee39b7f15d.png)

#### core
Contains the common code that is used across the entire application e.g navigation, screen routes, sizing, theme, components, preferences and constants.

#### data
Contains the datasources, repository implementations and DTOs. The datasource has two packages, local and remote. The local package contains the room database setup and dao. The remote package contains the two api interfaces for sending network requests to two separate api endpoints. The repository package contains the repository implementations. This is where the data is mapped into different entities and then cached. The app follows the single source of truth principle. This is why only the cached data is sent to the UI layer.

#### domain
Contains a model package which hosts the different entities and a relations package for room relationships, a repository, a usecase and a util package.

#### presentation
Contains all the classes and packages related to the UI i.e components, viewModel, state holders and screens

### User Interface

https://user-images.githubusercontent.com/82439687/221413047-890cf534-28b1-4936-92c6-3920e4f84e57.mp4

## License

Currency Budy is open-source and released under the MIT license. See the [LICENSE](license.txt) file for details.
