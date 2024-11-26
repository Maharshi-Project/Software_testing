# **Unit Converter API with Data Flow Graph Testing**

## **Project Overview**
This project implements a unit converter API that uses **graph-based testing with data flow criteria**. The primary focus is to design test cases based on **DU-paths (Definition-Use paths)** and achieve:
- **All-DU-paths coverage**


The project includes:
1. Unit conversion services (e.g., area, energy, temperature).
2. Test cases for du-path coverage.


---

## **Problem Statement**
The project aims to create **data flow graphs** and use them to achieve test coverage based on DU-paths. The test cases are designed to ensure the correctness of the program, including loops and multiple paths. 

Key objectives:
- Define **DU-paths** in the presence of loops.
- Use testing to achieve **all-DU-paths coverage** and **all-defs coverage**.
- Validate coverage using **JaCoCo** and graph-based analysis.

---

## **Features**
- REST API for unit conversions using **Spring Boot**.
- Conversion services include:
  - Area
  - Energy
  - Temperature
  - Frequency
- Data flow graphs for DU-path testing.
- Test case generation using JUnit.
- Coverage analysis using JaCoCo.

---

## **Technologies Used**
- **Java** (Spring Boot Framework)
- **JUnit 5** for test cases
- **JaCoCo** for code coverage
- **Maven** for build automation

---
