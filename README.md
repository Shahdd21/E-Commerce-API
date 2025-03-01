# E-Commerce Backend System
A secure and scalable e-commerce REST API built with Java & Spring Boot, featuring role-based authentication, product management, order processing, an e-Wallet system, and more.

## Features
**Customers**
  - Register & log in (JWT-based authentication)
  - View profile & wallet balance
  - Browse products, categories, and vendors
  - Manage orders (place, cancel, view)
  - Make payments (e-Wallet & other methods)
  - Add/edit/delete product reviews & ratings
  - Manage a wishlist

**Vendors**
- Register (admin approval required) & log in
- Manage products (add, update, delete)

**Admins**
- Super admin adds other admins
- Manage categories (add, edit, update)
- Manage orders (view all, update status)
- Delete inappropriate products

## Tech Stack
- Backend: Java, Spring Boot, Spring Security, JWT
- Database: MySQL
- API Tools: Spring Data JPA, Hibernate, Lombok
- Error Handling: Custom exceptions, global exception handler
- Security: Password hashing, role-based access control

## API Documentation
- Authentication: /auth/register/customer, /auth/register/vendor, /auth/login
- Admin : /admin/users, /admin/users/{id}, /admin/vendors/approve/id
- Orders: /orders, /orders/{id}, /orders/{id}/cancel, /orders/all, /orders/{id}/status
- Products: /products, /products/{id}, products/{id}/category/{id}, products/category/{id}, products/{id}/vendor/{id}, products/vendor/{id}
- Payments: /payments/{orderId}
- Wishlist: /wishlist, /wishlist/{productId}
- Wallet : /wallet, /wallet/topup
- Vendor : /vendors
- Review : /reviews/{id}
- Customer : /customer
- API details can be tested with Postman or Swagger (if configured)

## Setup & Installation
1. Clone the repository:
   ``` bash
   git clone https://github.com/your-username/e-commerce-api.git
   cd e-commerce-api
   ```
2. Configure the database in application.properties:
      ``` properties
      spring.datasource.url=jdbc:mysql://localhost:3306/your_db
      spring.datasource.username=root
      spring.datasource.password=your_password
      ```
3. Build & run the project:
      ``` bash
   mvn clean install
   mvn spring-boot:run
   ```
