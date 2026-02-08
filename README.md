# 📚 Online Book Store

This is a modern RESTful API for an online book store, developed on the basis of **Spring Boot**. The project implements
a full cycle of working with books: from browsing the catalog to securely placing orders through the cart.

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

## ![img_2.png](img_2.png) Access rights

Note: All endpoints are available to the user and are also available to the admin.

|                                        | ADMIN                                           |                                                           |
|----------------------------------------|-------------------------------------------------|-----------------------------------------------------------|
| **Book controller**                    | **Category controller**                         | **Order controller**                                      |                                                           
| POST /books - create new book          | POST /categories - create new category          | PATCH /orders/{id} - updates order status by specified id |                                                           
| PUT /books/{id} - update book by id    | PUT /categories/{id} - update category by id    |                                                           |
| DELETE /books/{id} - delete book by id | DELETE /categories/{id} - delete category by id |                                                           |

|                                                                                  | USER                                                                     |                                                                                    |
|----------------------------------------------------------------------------------|--------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| **Book controller**                                                              | **Category controller**                                                  | **Order controller**                                                               |
| GET /books - get a list of all available books                                   | GET /categories - get a list of all available categories                 | POST /orders - place a new order                                                   |
| GET /books/{id} - get a book by specified id                                     | GET /categories/{id} - get a category by specified id                    | GET /orders - get orders history                                                   |
| GET /books/search/... - returns a list of books with specified search parameters | GET /categories/{id}/books - get all books with specified category by id | GET /order/{orderId}/items - get all items in specified order by orderId           |
|                                                                                  |                                                                          | GET /order/{orderId}/items/{id} - get info about specified item in specified order |

|                 | Shopping Cart Controller                                  |                 |
|-----------------|-----------------------------------------------------------|-----------------|
|                 | GET /cart - get all items that cart contains              |                 |                                            
|                 | POST /cart - add book to cart                             |                 |                                            
|                 | PUT /cart/items/{id} - update already added book quantity |                 |
|                 | DELETE /cart/items/{id} - deletes item by ID from cart    |                 |

## 💡 Example of work

### Adding a book to the database (POST /books)

Note: A category must be created before this.

You also can see an example of work by this link (video: 2min:49sec): https://www.loom.com/share/cf643ad6e5624908bcfd1339a6a31641 

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

## 🤝 Contacts

GitHub: https://github.com/Jelors

Project Link: https://github.com/Jelors/online-book-store