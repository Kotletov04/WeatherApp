Что использовал:
Room, Retrofit, Kotlin Coroutines, Flow, Jetpack Compose, Hilt, Clean Architecture, MVVM

Для иконок погоды не стал использовать coil и иконки из api, а отрисовал их самостоятельно, это было сделано так как реализовывал логику работы приложения в отсутствие интернета 
Создал поиск городов, использовал для этого api 2gis, это позволило не нагружать приложение на 50мб файлом со списком всех городов. По выбранному городу выводится список городов с прогнозом, также есть возможность вернуться на экран выбора города и выбрать другой. 
Также была реализована отработка всех ошибок

Предложения по улучшению:
- Сделать bottombar
- появление экран поиска города, только при первом входе в приложение для установки города нахождения и сделать дополнительный экран с поиском города, для смены города находдения и списком городов, которые были ранее выбраны
- Более подробная информация по погоде, вывод почасовой погоды, а также вывод подробной погоды по прогнозу
