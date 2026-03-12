# 💼 Job Recommender CLI

A simple Command Line Interface (CLI) application built in Java ☕ that simulates a job recommendation system. 

The application allows you to create user profiles (with skills and experience) 🧑‍💻, publish job vacancies 🏢, and run a matching algorithm to suggest the most relevant open positions for a specific user ✨.

## 🌟 Features
* 💾 **In-Memory Storage:** Fast, session-based storage of users and vacancies.
* 🛡️ **Entity Duplication Prevention:** Safely ignores attempts to create users or jobs with identical names.
* 🧠 **Smart Matching Algorithm:** Calculates a compatibility grade based on the intersection of a user's skills and a job's required tags. Penalizes the score by 50% if the user lacks the required years of experience.

## 🛠️ Available Commands

* 👤 `user <name> --skills=<skill1,skill2> --exp=<years>`
  Adds a new user to the system. 
  *Example: `user alice --skills=java,ml,linux --exp=2`*

* 💼 `job <title> --company=<name> --tags=<tag1,tag2> --exp=<years>`
  Publishes a new job vacancy.
  *Example: `job Backend_Dev --company=VK --tags=java,backend,linux --exp=1`*

* 👥 `user-list`
  Prints all registered users, their skills (alphabetically sorted), and experience.

* 📋 `job-list`
  Prints all published job vacancies and their respective companies.

* 🎯 `suggest <username>`
  Evaluates the user against all available jobs and prints the top 2 best-matching vacancies.

* 🚪 `exit`
  Terminates the application loop.

## 🏗️ Architecture
This project is built using standard Object-Oriented Programming (OOP) principles, strongly adhering to the **Tell, Don't Ask** principle. The system is cleanly divided into:
* 🧱 **Domain:** Core business logic and entities (`User`, `Vacancy`, `Skill`, `Experience`, `Grade`).
* 🗄️ **Storages:** In-memory databases mapping names to objects.
* ⚙️ **Services:** The `Suggester` service handles the sorting and limiting of recommendations.
* 🕹️ **Controller:** Parses CLI input and routes commands to the appropriate layer.
