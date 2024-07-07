# Stephen King Project

## Table of Contents

- [Introduction](#introduction)
- [Used APIs](#used-apis)
- [Installation](#installation)
- [Usage](#usage)
- [Available Endpoints](#available-endpoints)
- [Credits](#credits)

## Introduction

A project dedicated to Stephen King. The application allows the user to see books published by Stephen King and detailed
information about each book, such as description, year of publication and number of pages. It also allows users to sort
and search for books. In addition, the app uses the TMDb API to retrieve information on movies and TV series based on
King's novels

## Used APIs

- [Stephen King](https://stephen-king-api.onrender.com/)
- [TMDB](https://www.themoviedb.org/)

This product uses the TMDB API but is not endorsed or certified by TMDB.

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/iamruppert/stephen-king.git
    ```
2. Navigate to the project directory:
    ```sh
    cd stephen-king
    ```
3. Build the project using Gradle:
    ```sh
    ./gradlew build
    ```

## Usage

To run the project, execute:

```sh
./gradlew run
```

## Available Endpoints

- ### <b>GET /api/books </b> <br>

  Retrieve all books, with optional sorting. <br>

    - ### Parameters:
        - `sortBy` (default: "pages"): Sort by attribute.
        - `sortOrder` (default: "asc"): Sort order (ascending/descending).

- ### <b>GET /api/search </b> <br>

  Search for books by title, with optional sorting. <br>

    - ### Parameters:
        - `name`: Name of the book.
        - `sortBy` (default: "pages"): Sort by attribute.
        - `sortOrder` (default: "asc"): Sort order (ascending/descending).

- ### <b>GET /api/book/{id} </b> <br>

  Retrieve details of a specific book by its ID. <br>

- ### <b>GET /api/tmdb/movies </b> <br>

  Retrieve all movies and TV series that are based on Stephen King's novels. <br>

- ### <b>GET /api/tmdb/movie/{id} </b> <br>

  Retrieve details of a specific movie by its ID. <br>

## Credits

<br>

![Alt text](https://www.themoviedb.org/assets/2/v4/logos/v2/blue_long_2-9665a76b1ae401a510ec1e0ca40ddcb3b0cfe45f1d51b77a308fea0845885648.svg)