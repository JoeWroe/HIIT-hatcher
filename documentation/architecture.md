# Architecture :nut_and_bolt:

## Overview

The HIIT Hatcher API currently consists of a Spring Boot Web Application, written in Java 11, sitting on top of MongoDB for persistence.

## Endpoints

|Endpoint               |Method          |Response                                                    |
|:---------------------:|:--------------:|:-----------------------------------------------------------|
|`/api/exercises`       |**GET**         |_Returns a list of all exercises currently in the database._|
|`/api/exercises`       |**POST**        |_Creates a new exercise using the given body._              |
|`/api/exercises/{name}`|**GET**         |_Returns the exercise with the name given in the URL._      |