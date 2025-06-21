Important: Don’t forget to retrieve the bearer authentication token from localStorage (after login or registration) and include it in the Authorization header for protected endpoints like adding a book, uploading a cover image, etc

Book Management Application This application is designed for managing books – it includes a REST API to store, update, and retrieve book data. It's built with Spring Boot and uses a PostgreSQL database.

🐳 Run with Docker
To start the application using Docker, simply navigate to the docker folder and run:
docker-compose up
This will automatically build and start all necessary containers. The app will be available at: http://localhost:8088

✉️ Email Verification The application includes functionality for email-based account verification.

A local email client (like MailDev) is available at:

👉 http://localhost:1080/

All emails sent by the app (e.g., confirmation links) will appear there.

🧪 Testing the API with Postman You can test all REST endpoints using Postman.

Some main endpoints:

Method Path Description

GET /api/v1/books Get a list of all books

POST /api/v1/save-book Add a new book

GET /api/v1/books/{id} Get book by ID

POST /api/v1/books/cover/{bookId} Upload book cover (Multipart form)

📸 Uploading a Book Cover (Multipart Request) In Postman:

Method: POST

URL: http://localhost:8088/api/books/cover/{bookId}

Body: form-data

Key: file → type: File → choose an image file

Seed Data for Development INSERT INTO book (title, author, description, no_of_pages, no_of_copies, isbn, language, created_date, last_modified_date, created_by, last_modified_by) VALUES ('The Hobbit', 'J.R.R. Tolkien', 'A fantasy novel about a hobbit’s adventure.', 310, 5, '978-0261103344', 'English', '2023-01-01 10:00:00', '2023-01-05 12:00:00', 1, 1),

('1984', 'George Orwell', 'Dystopian novel set in a totalitarian regime.', 328, 3, '978-0451524935', 'English', '2023-02-01 09:30:00', '2023-02-03 14:15:00', 1, 1),

('Meno vetra', 'Patrick Rothfuss', 'Príbeh Kvotheho, veľkého čarodeja a hudobníka.', 662, 2, '978-8025710358', 'Slovak', '2023-03-10 15:45:00', '2023-03-11 16:00:00', 1, 1),

('Na západe nič nové', 'Erich Maria Remarque', 'Klasický protivojnový román.', 296, 4, '978-3455404063', 'German', '2023-04-20 08:00:00', '2023-04-25 11:30:00', 1, 1),

('Sto rokov samoty', 'Gabriel García Márquez', 'Magický realizmus o rodine Buendíovcov.', 417, 6, '978-8437604947', 'Spanish', '2023-05-05 13:20:00', '2023-05-10 10:10:00', 1, 1);
