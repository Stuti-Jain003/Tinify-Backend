# Tinify Backend

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Introduction

Tinify Backend is the server-side component of the Tinify URL shortener service. It is built using Java with Spring Boot and communicates with the Tinify Frontend to shorten URLs and manage URL redirects. It also utilizes Redis for fast data access and storage.

## Features

- Shortens URLs
- Redirects to original URLs
- Uses Redis for quick data retrieval

## Technologies Used

- **Backend**: Java, Spring Boot
- **Database**: Redis
- **Build Tool**: Maven

## Installation

### Prerequisites

- Java 11 or higher
- Maven 3.6.x or higher
- Redis server

### Setup

1. Clone the repository:

   ```sh
   git clone https://github.com/yourusername/tinify-backend.git
   cd tinify-backend