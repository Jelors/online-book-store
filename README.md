# 📚 Online Book Store

# ![img.png](img.png) ENG 

This is a modern RESTful API for an online book store, developed on the basis of **Spring Boot**. The project implements a full cycle of working with books: from browsing the catalog to securely placing orders through the cart.

## 🚀 Main features

- **Authentication (JWT):** Secure access to the API using JWT tokens (Bearer Authentication).
- **Book Catalog:** Manage books and their categories.
- **Shopping Cart:** Add, remove and change the number of books in the cart.
- **Orders:** Implement a shopping system and log orders.
- **Validation:** Validate input data at the DTO level.

---

## 🛠 Technology stack

- **Java 17+**
- **Spring Boot 3**
- **Spring Security** (JWT Authentication)
- **Spring Data JPA**
- **MySQL** (Database)
- **Docker & Docker Compose**
- **Maven** (Build Tool)
- **Swagger UI** (API Documentation)

---

## 📦 Installation and launch

### Option 1: Run via Docker (Recommended)

To bring up the application along with the MySQL database with one command:

1. Clone the repository:
   ````bash
   git clone [https://github.com/Jelors/online-book-store.git](https://github.com/Jelors/online-book-store.git)
   cd online-book-store
   ````

2. Start the containers:
    ````bash
   docker-compose up -d
   ````

### Option 2: Local launch

1. Assemble the project
    ````bash
   mvn clean package
   ````

2. Install dependencies
    ````bash
   mvn clean install
   ````

3. Launch the app
    ````bash
   mvn spring-boot:run 
   ````

---

## 📑 API Documentation (Swagger)

After launching the service, you can view all available endpoints and test them at the link:
🔗 http://localhost:8080/api/swagger-ui/index.html

### How to use:

1. Registration: Complete the request to create an account.
   ````bash 
   POST auth/register 
   ````

2. Login: Complete the login request using your email and password.
   ````bash
   POST auth/login
   ````

3. Token: Copy the received token from the response.
4. Authorization: Click the Authorize button in Swagger.
5. Login: In the Value field, paste the token and click Authorize.

Now you can use all the functionality of the service.

---

## 💡 Example of work

### Adding a book to the database (POST /books)

Note: A category must be created before this.

   ````json
   {
  "title": "A Knight of The Seven Kingdoms",
  "author": "George R. R. Martin",
  "isbn": "9780008238094",
  "price": 349,
  "description": "Almost a century before A Game of Thrones...",
  "coverImage": "akotsvk.png",
  "categoryIds": [
    1
  ]
}
 ````

---

## 🗄 Database

The project uses a normalized database schema that includes:

- **User, CartItem, Category, Order, OrderItem, Role, ShoppingCart, User tables**
- **Book Catalog with Category Binding**
- **Cart and Order Bindings (One-to-Many / Many-to-Many)**

---
# ![img_1.png](img_1.png) UA 

Це сучасний RESTful API для книжкового інтернет-магазину, розроблений на базі **Spring Boot**. Проєкт реалізує повний
цикл роботи з книгами: від перегляду каталогу до безпечного оформлення замовлень через кошик.

## 🚀 Основні можливості

- **Автентифікація (JWT):** Безпечний доступ до API за допомогою JWT-токенів (Bearer Authentication).
- **Каталог книг:** Управління книгами та їх категоріями.
- **Кошик (Shopping Cart):** Додавання, видалення та зміна кількості книг у кошику.
- **Замовлення (Orders):** Реалізація системи покупок та логування замовлень.
- **Валідація:** Перевірка вхідних даних на рівні DTO.

---

## 🛠 Технологічний стек

- **Java 17+**
- **Spring Boot 3**
- **Spring Security** (JWT Authentication)
- **Spring Data JPA**
- **MySQL** (Database) 
- **Docker & Docker Compose**
- **Maven** (Build Tool)
- **Swagger UI** (API Documentation)

---

## 📦 Встановлення та запуск

### Варіант 1: Запуск через Docker (Рекомендовано)

Для того щоб підняти додаток разом із базою даних MySQL однією командою:

1. Склонуйте репозиторій:
   ````bash
   git clone [https://github.com/Jelors/online-book-store.git](https://github.com/Jelors/online-book-store.git)
   cd online-book-store
   ````

2. Запустіть контейнери:
    ````bash
   docker-compose up -d
   ````

### Варіант 2: Локальний запуск

1. Зберіть проєкт
    ````bash
   mvn clean package
   ````

2. Встановіть залежності
    ````bash
   mvn clean install
   ````

3. Запустіть додаток
    ````bash
   mvn spring-boot:run 
   ````

---

## 📑 Документація API (Swagger)

Після запуску сервісу ви можете переглянути всі доступні ендпоінти та протестувати їх за посиланням:
🔗 http://localhost:8080/api/swagger-ui/index.html

### Як користуватися:

1. Реєстрація: Виконайте запит, щоб створити акаунт.
   ````bash 
   POST auth/register 
   ````

2. Логін: Виконайте запит для входу, використовуючи вашу пошту та пароль.
   ````bash
   POST auth/login
   ````

3. Токен: Скопіюйте отриманий token з відповіді.
4. Авторизація: Натисніть кнопку Authorize у Swagger.
5. Вхід: У поле Value вставте токен і натисніть Authorize.

Тепер ви можете користуватись всім функціоналом сервісу.

---

## 💡 Приклад роботи

### Додавання книги в БД (POST /books)

Примітка: перед цим повинна бути створена категорія.

   ````json
   {
  "title": "A Knight of The Seven Kingdoms",
  "author": "George R. R. Martin",
  "isbn": "9780008238094",
  "price": 349,
  "description": "Almost a century before A Game of Thrones...",
  "coverImage": "akotsvk.png",
  "categoryIds": [
    1
  ]
}
 ````

---

## 🗄 База даних

Проєкт використовує нормалізовану схему бази даних, що включає:

- **User, CartItem, Category, Order, OrderItem, Role, ShoppingCart, User таблиці**
- **Каталог книг із прив'язкою до категорій**
- **Зв'язки для кошиків та замовлень (One-to-Many / Many-to-Many)**

## 🤝 Contacts / Контакти

GitHub: https://github.com/Jelors

Project Link: https://github.com/Jelors/online-book-store