Ticket Tracking System

A modern Java Swing-based Ticket Management System designed for IT departments and organizations to efficiently manage support requests, asset inventories, and technician assignments.
Built with a glass UI effect, gradient backgrounds, and responsive layout, this app features role-based access for Admins, Technicians, and Users.

------------------------------------------------------------
Features
------------------------------------------------------------

ADMIN PANEL
• View and manage all tickets created by users.
• Assign tickets to technicians.
• Track Date Received, Date Assigned, and Status.
• Manage pending account requests (approve or reject).
• Access company asset inventory with:
  - Item Name
  - Inventory Number
  - Shelf / Location
  - Status (Working / Defective)
  - Assigned To

TECHNICIAN PANEL
• View tickets assigned to that technician.
• Update ticket status (In Progress, Fixed, Closed).
• See assigned time and date.

USER PANEL
• Create and view personal tickets.
• Each ticket includes ID, Title, Description, Progress.
• Track updates in real time.

SYSTEM & UI FEATURES
• Role-based dashboards (Admin, Tech, User, Head IT)
• Collapsible sidebar with logout and settings buttons
• Dark / Light Mode toggle
• Sound toggle in settings
• Tinted glass-like UI inspired by macOS
• Background image support (customizable)
• Responsive layout that adapts to window resizing

------------------------------------------------------------
Project Structure
------------------------------------------------------------

src/
 └── main/
     ├── java/
     │   └── com/example/ticketstracker/
     │       ├── controller/   - logic (Auth, Ticket, Account, Theme)
     │       ├── model/        - data classes (User, Ticket, Role)
     │       └── ui/           - all Swing panels
     └── resources/            - images and assets

------------------------------------------------------------
Requirements
------------------------------------------------------------

• Java 17 or higher
• Gradle build system
• IntelliJ IDEA (recommended)

To check versions:
java -version
gradle -v

Expected:
openjdk version "17.0.11"
Gradle 8.7

------------------------------------------------------------
How to Run
------------------------------------------------------------

1. Clone the repository:
   git clone https://github.com/<your-username>/TicketsTracker.git

2. Open in IntelliJ IDEA.
   IntelliJ will automatically detect Gradle.

3. Build the project:
   ./gradlew build

4. Run the app:
   Run Main.java in src/main/java/com/example/ticketstracker/ui/

------------------------------------------------------------
Default Login Accounts
------------------------------------------------------------

Role         | Username | Password
-----------------------------------
Admin        | admin    | admin
Head IT      | headit   | headit
Technician 1 | tech1    | tech1
Technician 2 | tech2    | tech2
Technician 3 | tech3    | tech3
Technician 4 | tech4    | tech4
User 1–4     | user1–4  | same as username

------------------------------------------------------------
Customization
------------------------------------------------------------

Background Image:
Replace the file in src/main/resources/background.jpg

Style Customization:
Edit Style.java to adjust fonts, colors, button gradients, and transparency.

------------------------------------------------------------
Example Gradle Build Script
------------------------------------------------------------

plugins {
    id("java")
    application
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

application {
    mainClass.set("com.example.ticketstracker.ui.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.swinglabs:swingx:1.6.1")
}

------------------------------------------------------------
Future Enhancements
------------------------------------------------------------

• Database integration (SQLite / MySQL)
• Email or push notifications
• Export reports (PDF, Excel)
• Analytics dashboard for ticket metrics
• Role-based access logging

------------------------------------------------------------
Developer
------------------------------------------------------------

David Harris
MSc Cyber Security | UWE Bristol
Email: davidharris200111@gmail.com

------------------------------------------------------------
License
------------------------------------------------------------

This project is licensed under the MIT License.
You are free to use, modify, and distribute this software for personal purposes.

------------------------------------------------------------
Built with love, Swing, and a touch of glass by David Harris (2025)
------------------------------------------------------------
