# 🚌 Bus Reservation System

A full-stack web application built using  for online bus ticket booking. This project allows users to select buses on predefined routes, enter passenger details, and receive ticket confirmations via email.

---

## 📸 Screenshots

### 🏠 Home Page  
![Home Page](/screen_shorts/home page.png)

### 📋 Available Buses  
![Bus List](/images/bus-list.png)

### 🪑 Seat Selection  
![Seat Selection](/images/seat-selection.png)

### ✅ Booking Confirmation Popup  
![Confirmation Popup](/images/confirmation-popup.png)

### 📧 Confirmation Email  
![Confirmation Email](/images/confirm-email.png)

---

## 🚀 Features

- View available buses with **name, route, time, and seat availability**
- Enter passenger details: **Name, Gender, Email**
- **Auto-send confirmation emails** with trip info using JavaMail
- Clean UI layout: left pane for user input, right for bus details
- Responsive and styled form using HTML/CSS + Thymeleaf
- Displays static seat selection or count dynamically (if extended)

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot, Spring MVC
- **Frontend:** Thymeleaf, HTML, CSS
- **Email Service:** JavaMail + SMTP
- **Build Tool:** Maven

---

## 📦 Project Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/bus-reservation-system.git
   cd bus-reservation-system
