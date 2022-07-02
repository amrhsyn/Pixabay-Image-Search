
#  Description

A sample pixabay image search project contains a search screen with result list and a detail screen with selected image detail.
<br />
you can download a debug version here : [debug version ](https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/app-debug.apk)

#  Stack :
- Kotlin
- Compose + Flow
- Coroutines
- Clean architecture + MVVM
- Multi module (feautred-layerd-base)
- Retrofit + Gson
- Room
- Paging3
- Jdk (for api_key security)
- kotlin dsl

#  Screenshots
search screen :
<br />
<img src="https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/screenshots/11.png" width="25%">
<img src="https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/screenshots/77.png" width="25%">
<img src="https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/screenshots/55.png" width="25%">
<br />
<br />
<br />
details screen:
<br />
<img src="https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/screenshots/44.png" width="25%">
<img src="https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/screenshots/33.png" width="25%">
<br />
<br />
<br />
error and loading states:
<br />
<img src="https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/screenshots/22.png" width="25%">
<img src="https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/screenshots/66.png" width="25%">


#  Module Design

| Module name | Description |

| ------------- | ------------- |

| [app](/app/) | main activity, application class, navigation, and End2End tests |

| [test_shared](/test_shared/) | some resources for testing in other modules |

| [core](/core/)  | core business models and util classes. |

| [core_ui](/core_ui/)  | core ui utils classes. |

| [imageSearch presentation](/image_search/image_search_presentation/)  | image search ui contains compose files, viewmodels and etc ... |

| [imageSearch domain](/image_search/image_search_domain) | image search domain layer contains repository interfaces and usecases calasses |

| [imageSearch data ](/image_search/image_search_data/) | image search data layer contains repositories implementation, retrofit, dto files, mappers and paging classes |

| [imageDetails presentation ](/image_detail/image_detail_presentation/)  | image detail ui contains compose files, viewmodels and etc .... |

#  Architecture
- I used MVVM + Clean Architecture, I have three separated modules named presentation, domain, data for each feature module, with this I have strict separation in my layers and they don't access each other I implemented all clean architecture concepts because the project was an assignment and I think it's over-engineering for this project.
-

#  And
- because of time i couldn't write tests for all parts and i just created a test for search view model [here ](https://github.com/amrhsyn/Pixabay-Image-Search/blob/develop/image_search/image_search_presentation/src/test/java/me/ahch/image_search_presentation/SearchViewModelTest.kt) and an End to End test [here](https://github.com/amrhsyn/Pixabay-Image-Search/tree/develop/app/src/androidTest/java/me/ahch/pixabaysearch).
- I used Hilt as my DI library because I think Hilt has fewer boilerplate codes than dagger2 but I could use dagger2 or even koin too.
- I used Coroutines and Flows for app threading and observing because it's lighter than Rx, it's native and integrated with other google libraries and it's easier to test because google has some libraries for testing them.
- For UI/UX, I tried to keep it simple, I used material design and free assets
- I used git-flow as my git strategy, I created main, develop, feature/search, and feature/image_detail branches
- generaly speaking, because of time limitation i couldn't develop perfect and many things can be improved
