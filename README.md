# RA2311003011232_Bajaj_Finserv_Health_Java_Qualifier

# Quiz Leaderboard System (Java)
## Features Implemented

The Quiz Leaderboard System project successfully implements the Quiz Leaderboard assignment through the following features:

- API call
- Deduplication of data
- Score calculation
- Leaderboard creation
- Submission of result

---

## Tech Stack

- Java Programming Language
- HTTPURLConnection for API requests
- HashSet & HashMap classes for logic implementation

---

## Workflow

- Makes API request **10 times** (i.e., `poll = 0 to 9`)
- Rests for **5 seconds** after each request
- Stores all events of quizzes
- Eliminates duplicate data based on:

  ```
  (roundId + participant)
  ```

- Determines total score of each participant
- Creates the leaderboard (based on highest score)
- Sends results to API

---

## Important Points to Note

* **Deduplication**

  * Utilizes a `HashSet` class to prevent multiple instances of same entry
  
* **Score Calculation**

  * Utilizes a `HashMap` class to calculate total score of participants

---

## Code Execution

```bash
javac Main.java
java Main
```

---

## Author

- Soumyabrata Bose [RA2311003011232]

* Soumyabrata Bose [RA2311003011232]
