# RatesAndGifs
Данный репозиторий сделан как тестовое задание для вакансии.

## Суть задания
Создать сервис, который обращается к сервису курсов валют, и отображает gif:  
если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich  
если ниже - отсюда https://giphy.com/search/broke  
Ссылки  
REST API курсов валют - https://docs.openexchangerates.org/  
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide  

Сервис на Spring Boot 2 + Java
Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD
Для взаимодействия с внешними сервисами используется Feign
Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
На сервис написаны тесты
Для сборки должен использоваться Gradle

## Запуск

После запуска проекта в среде разработки запуск html через
```
localhost:8080
```

В application.properties указаны API ключи, можно указать свои
