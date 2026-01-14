# Flight Information Management System

This Java program processes a dataset of flight information to provide insights and search capabilities.

## Dataset
The dataset is located at `data/dataset.csv`. It contains flight details including:
- Customer demographics (Age, Gender, Type)
- Flight details (Class, Distance, Date)
- Satisfaction ratings
- Delays

## Project Structure
- `src/`: Java source files
- `bin/`: Compiled Java classes
- `data/`: CSV dataset

## How to Run
1. Compile the project:
   ```bash
   mkdir -p bin
   javac -d bin src/*.java
   ```

2. Run the application:
   ```bash
   java -cp bin Main
   ```

## Features
- View total number of records
- Calculate average arrival delay
- List records by flight class
- Find the longest flight
- Search for a specific flight by ID
- Count satisfied customers
