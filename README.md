# IT221 Lab Activity 0 - Airline Passenger Satisfaction Data Processing

## Information Management - Module 1: Data Processing and Databases

A Java application that processes an airline passenger satisfaction dataset, demonstrating data processing concepts using different data types (String, Integer, Decimal).

---

## 📊 Dataset Information

**File:** `data/airline_satisfaction.csv`

| Attribute | Details |
|-----------|---------|
| **Total Records** | 25,976 rows |
| **Total Columns** | 25 columns |
| **Source** | Airline Passenger Satisfaction Survey |

### Data Types Used:

| Type | Columns |
|------|---------|
| **String** | Gender, Customer Type, Type of Travel, Class, Satisfaction |
| **Integer** | ID, Age, Flight Distance, Service Ratings (0-5 scale) |
| **Decimal** | Departure Delay in Minutes, Arrival Delay in Minutes |

### Column Descriptions:

1. **ID** - Unique passenger identifier
2. **Gender** - Male/Female
3. **Customer Type** - Loyal Customer / Disloyal Customer
4. **Age** - Passenger age (integer)
5. **Type of Travel** - Business travel / Personal Travel
6. **Class** - Business / Eco / Eco Plus
7. **Flight Distance** - Distance in miles (integer)
8. **Service Ratings (0-5):** Inflight Wifi Service, Departure/Arrival Time Convenient, Ease of Online Booking, Gate Location, Food and Drink, Online Boarding, Seat Comfort, Inflight Entertainment, On-board Service, Leg Room Service, Baggage Handling, Check-in Service, Inflight Service, Cleanliness
9. **Departure Delay in Minutes** - Delay time (decimal)
10. **Arrival Delay in Minutes** - Delay time (decimal)
11. **Satisfaction** - satisfied / neutral or dissatisfied

---

## 📁 Project Structure

```
workspace/
├── README.md                           # Project documentation
├── data/
│   └── airline_satisfaction.csv        # Dataset file (25,976 records)
└── src/
    └── main/
        └── java/
            ├── Main.java               # Main application with menu
            ├── model/
            │   └── PassengerRecord.java    # Data model class
            ├── processor/
            │   └── DataProcessor.java      # Data processing logic
            └── util/
                └── CSVReader.java          # CSV file reader utility
```

---

## 🚀 How to Run

### Prerequisites
- Java JDK 11 or higher

### Compilation

```bash
# Navigate to project root
cd /workspace

# Compile all Java files
javac -d out src/main/java/model/*.java src/main/java/util/*.java src/main/java/processor/*.java src/main/java/Main.java
```

### Execution

```bash
# Run the program
java -cp out Main
```

### Quick Start (Combined)

```bash
cd /workspace && javac -d out src/main/java/model/*.java src/main/java/util/*.java src/main/java/processor/*.java src/main/java/Main.java && java -cp out Main
```

---

## 📋 Features & Menu Options

### Main Menu
1. **Dataset Overview** - View dataset summary and statistics
2. **Demographics Analysis** - Gender, age, customer type distributions
3. **Flight Statistics** - Travel class, distance, delays analysis
4. **Service Ratings Analysis** - Average ratings for all 14 services
5. **Satisfaction Analysis** - Satisfaction rates by various categories
6. **Search & Filter** - Search by ID, filter by class/age
7. **Comprehensive Report** - Full dataset summary report

### Analysis Capabilities
- Distribution analysis (counts and percentages)
- Statistical summaries (min, max, average)
- Category-based satisfaction rate analysis
- Service rating rankings (top/bottom performers)
- Custom filtering by multiple criteria
- Individual record lookup

---

## 💻 Class Descriptions

### `Main.java`
Entry point with interactive menu system. Handles user input/output and coordinates data processing operations.

### `model/PassengerRecord.java`
Data model representing a single passenger record. Contains:
- All 25 data fields with appropriate data types
- Getters and setters for all properties
- Helper methods (`isSatisfied()`, `getAverageServiceRating()`)

### `util/CSVReader.java`
Utility class for reading and parsing the CSV file:
- Handles file I/O operations
- Parses CSV lines into PassengerRecord objects
- Type conversion with error handling

### `processor/DataProcessor.java`
Core data processing logic:
- Statistical calculations (distributions, averages)
- Filtering and searching operations
- Report generation
- Uses Java Streams API for efficient data processing

---

## 📈 Sample Output

```
╔════════════════════════════════════════════════════════════╗
║    AIRLINE PASSENGER SATISFACTION DATA PROCESSING SYSTEM   ║
║                   IT221 - Lab Activity 0                   ║
╚════════════════════════════════════════════════════════════╝

✓ Dataset loaded successfully!
  Total records: 25,976

┌──────────────────────────────────────────┐
│              MAIN MENU                   │
├──────────────────────────────────────────┤
│  1. Dataset Overview                     │
│  2. Demographics Analysis                │
│  3. Flight Statistics                    │
│  4. Service Ratings Analysis             │
│  5. Satisfaction Analysis                │
│  6. Search & Filter Records              │
│  7. Generate Comprehensive Report        │
│  0. Exit                                 │
└──────────────────────────────────────────┘
```

---

## 🔧 Technical Details

- **Language:** Java 11+
- **Data Processing:** Java Streams API
- **Data Structures:** ArrayList, HashMap, LinkedHashMap
- **Design Pattern:** MVC-inspired (Model-Processor-View)

---

## 👨‍💻 Author

IT221 Student - Information Management Lab Activity 0

---
