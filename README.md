# Project2Attraction
Attractions in the Cities.  Spring Boot, Hibernate, REST, Liquibase, PostgreSQL.


REST-сервис для хранения данных о городских достопримечательностях.

Структура данных:

Город:

-----------------------------------------

Идентификатор (порядковый номер)

Название города

Численность населения

Наличие метро

Страна

-----------------------------------------
Достопримечательность

-----------------------------------------

Идентификатор

Название достопримечательности

Дата постройки

Краткое описание

Тип достопримечательности (enum, например: Здание/Сооружение/Музей/Памятник/Заповедник)

Идентификатор города

----------------------------------------

REST-сервис предоставляет следующие методы:

- Получить все достопримечательности http://localhost:8080/attractions  

- Получить все достопримечательности в отсортированном виде(без параметра фильтрации, т.к. не успел в срок) http://localhost:8080/sorted_attractions

- Получить все достопримечательности конкретного города http://localhost:8080/cities/{id} //{id} - id города

- Получить город по id http://localhost:8080/cities/city/{id}  - решил, что такие данные тоже важны и добавил возможность такого запроса

- Добавить город  http://localhost:8080/cities/create

- Добавить достопримечательность http://localhost:8080/attractions/create

- Изменение данных по городу (возможно изменение только полей: Численность населения, Наличие метро) - меняются все данные, ограничения на поля не успел сделать.

- Изменение данных по достопримечательности (возможно изменение только полей: Краткое описание) - меняются все данные, ограничения на поля не успел сделать.

- Удаление достопримечательности из справочника http://localhost:8080/attractions/delete/{id}

Сервис при первом запуске самостоятельно создаёт тестовые объекты в БД с помощью миграции Liquibase.


Запуск: 
Открыть maven проект в IDE и запустить.

После запуска делать запросы через Postman. Запросы описаны выше.

Для реализации  использовались Java 8, Spring Boot, Hibernate, PostgreSQL, Liquibase.
