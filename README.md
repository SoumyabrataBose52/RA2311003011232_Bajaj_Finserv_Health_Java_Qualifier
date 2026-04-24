# RA2311003011232_Bajaj_Finserv_Health_Java_Qualifier

# Quiz Leaderboard System (Java)
## Overview

This project solves the Quiz Leaderboard assignment by:

* Fetching data from an external API
* Removing duplicate entries
* Calculating total scores
* Generating a leaderboard
* Submitting the final result

---

## Tech Used

* Java
* HTTPURLConnection for API calls
* HashSet & HashMap for logic

---

## How It Works

1. Calls the API **10 times** (`poll = 0 to 9`)
2. Waits **5 seconds** between each call
3. Collects all quiz events
4. Removes duplicates using:

   ```
   (roundId + participant)
   ```
5. Calculates total score per participant
6. Sorts leaderboard (highest score first)
7. Submits final result to API

---

## Key Logic

* **Deduplication**

  * Uses a `HashSet` to avoid counting repeated entries

* **Score Aggregation**

  * Uses a `HashMap` to store total scores

---

## How to Run

```bash
javac Main.java
java Main
```

---

## Author

* Soumyabrata Bose [RA2311003011232]
