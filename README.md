# Currency Converter

A simple Currency Converter developed in Java 8 with Spring Web MVC, Spring Boot, using Fixer API, Axios, HTML5, CSS3 and Javascript (VanillaJS). This application was developed as part of a Full Stack Developer coding task.

## Main Characteristics

- A basic currency converter which converts values between Euro, US Dollar and Japanese Yen.

- The converter consists of two parts:
	1. a REST API endpoint implemented in Java
	2. an HTML user interface that uses JavaScript to interact with the Java API

- The Java API endpoint accept three inputs - the source currency, the target currency, and the amount - and respond with the result of the conversion. The Java API use the [Fixer API](http://fixer.io) to fetch the latest exchange rates.
To allow easy testing, the Java API was built as a standalone application (with an embedded HTTP server) using [Spring Boot](https://projects.spring.io/spring-boot/).

- In the JavaScript the Ajax calls are made with [`axios`](https://github.com/axios/axios) (which is included at the bottom of the `index.html` file). There is no other libraries or frameworks like jQuery, Angular, React, etc.

- The UI is designed to work for mobile devices.

-------

# Solution Details

- Author: Tiago Luna
- Job position: Full Stack Developer
- Date: Oct 22th, 2017

Please check my kanban board for this solution: [Kanban-Chi Board](https://drive.google.com/file/d/0Bze55yZhXWExUTFMZTJGRVhXZlk/view?usp=sharing)

## Technologies

* Java 8
* Spring Framework / Spring Boot
* Jackson
* JUnit / Mockito
* Tomcat
* Fixer API
* Axios
* HTML5 / CSS3 / Javascript (VanillaJS)

## How to run

Simply run `MyApplication.java` as a Java Application and access the URL `http://localhost:8080/`
