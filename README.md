# Scrabble Point Calculator  

This project consists of three components:  

1. **MySQL database (`scrabbledb`)**  
2. **Spring Boot backend**  
3. **React (Vite) frontend**  

Follow the steps below to set up and run everything locally.  

---

## 1. Database Setup (MySQL)  

1. Ensure MySQL is installed and running on **localhost:3306**.  
2. Create a database called **`scrabbledb`**.  
3. Create the `scores` table using the following DDL:  

   ```sql
   CREATE TABLE scores (
     id INT AUTO_INCREMENT PRIMARY KEY,
     word VARCHAR(32) NOT NULL,
     score INT NOT NULL,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   ```  

4. Set the MySQL username and password for `scrabbledb` to both be:  

   ```
   username: root
   password: root
   ```

---

## 2. Backend Setup (Spring Boot)  

1. Clone the repository and open it in **IntelliJ IDEA** (or your preferred IDE).  
2. The backend is a Spring Boot app with an embedded Tomcat server.  
3. Run the application as you would any standard Spring Boot app.  

Once running, the backend should be available on **http://localhost:8080**.  

---

## 3. Frontend Setup (React + Vite)  

1. Navigate to the frontend directory:  

   ```bash
   cd <your-repo-directory>\scrabble-monorepo\packages\frontend
   ```

2. Install dependencies:  

   ```bash
   npm install
   ```

3. Start the development server:  

   ```bash
   npm run dev
   ```

By default, the frontend should start on **http://localhost:5173**.  

---

## 4. Cross-Origin Configuration  

The backend allows requests from the frontend via the `@CrossOrigin` annotation.  

If the frontend runs on a **different port** (not 5173), update the port in  
`ScoreController.java`:  

```java
@CrossOrigin(origins = "http://localhost:5173/") // Dev Mode
```

Replace `5173` with the actual port from the frontend.  

---

## ✅ Ready to Use  

With MySQL, backend, and frontend running, the **Scrabble Point Calculator** will be ready for use at:  

👉 **Frontend:** http://localhost:5173  
👉 **Backend:** http://localhost:8080  
