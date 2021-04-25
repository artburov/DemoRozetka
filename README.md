# Демо-проект автоматизации тестирования веб-сайта
https://rozetka.com.ua/

# Используемые технологии
![Java](readme_files/Icons/Java.png)
![Gradle](readme_files/Icons/Gradle.png)
![JUnit5](readme_files/Icons/JUnit5.png)
![Selenide](readme_files/Icons/Selenide.png)
![Allure_Report](readme_files/Icons/Allure.png)
![Jenkins](readme_files/Icons/Jenkins.png)
![Selenoid](readme_files/Icons/Selenoid.png)
![Telegram](readme_files/Icons/Telegram.png)
- Java
- Gradle
- Junit5
- Selenide
- Allure
- Jenkins
- Selenoid
- Telegram Bot

# Описание
Тестирование UI авторизации на сайте с вводом различных значений.

- Тесты запущены из CI системы Jenkins
- Использован Selenoid для контейнеризации и кросс-браузерного тестирования
- Сгенерирован отчет Allure, автоматически добавлены логи,скриншоты браузера и видео прохождения тестов
- Уведомление с отчетом о прохождении тестов автоматически отправляется в Telegram

## Отчеты Allure reports
### Общий отчет о прохождении тест сьюта
![Allure reports overview](./readme_files/AllureReport.jpg)
### Детальный отчет о прохождении конкретного теста
![Allure reports test](./readme_files/AllureReportSomeTest.jpg)
### Видео прохождения одного из тестов
![Video](./readme_files/TestVideo.gif)
## Уведомление в Telegram
![telegram](./readme_files/TelegramBot.jpg)

## Ссылки
### Jenkins
https://jenkins.autotests.cloud/job/c05-art-burov-quick-project/
### Allure reports
https://jenkins.autotests.cloud/job/c05-art-burov-quick-project/3/allure

