#Интернет-магазин на Spring Framework

* Фронтенд на angular js
* Тестовая база данных h2
* Система миграции БД flyway
* Для хранения корзин на бэкэнде используется Redis
* Нет привязки к сессии пользователя, для хранения данных на клиенте используется localstorage

### Текущий функционал:
* Постраничное отображение списка товаров
* Возможность добавления / изменения / удаления товаров
* Добавление товаров в корзину и оформление заказа (в том числе без авторизации)
* Авторизация на сайте (тестовые пользователи user, admin, пароль 100)
* Профиль пользователя с отображением списка заказов
* Отображение статусов товаров
* Платежная система PayPal

### TODO:
- [x] Фильтры товаров
- [x] Сделать полноценный личный кабинет пользователя
- [ ] Страница товара
- [ ] Настроить Security
- [ ] Сделать админку
- [ ] Покрытие тестами
