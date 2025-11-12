# GEMINI.md

## Project Overview

This is a Clojure project for performing game theory calculations. The main goal of the project is to find pure and mixed strategy Nash equilibria for normal form games. The project is in an early stage of development.

The main technologies used are:
*   **Language:** Clojure
*   **Build Tool:** Leiningen
*   **Libraries:** 
    *   `net.mikera/core.matrix`: For linear algebra calculations.
    *   `net.mikera/vectorz-clj`: A high-performance vector and matrix library for Clojure.

The project is structured as follows:
*   `src/game_theory_calculator/core.clj`: The main entry point of the application.
*   `src/game_theory_calculator/prisoner_dilemma.clj`: Contains the core logic for representing and solving games.
*   `test/game_theory_calculator/core_test.clj`: The main test file.

## Design and Architecture

### Overall Approach

The project will follow a data-oriented and functional design approach. The core logic will be implemented as a set of pure functions that operate on immutable data structures representing the games.

### Data Representation

Clojure's built-in data structures are ideal for representing game data due to their immutability, which ensures function purity and simplifies state management.

*   **Normal Form Games:** A map will be used to hold player information, strategy sets, and a payoff matrix. The payoff matrix itself will be represented as a nested vector or a `core.matrix` type.
*   **Extensive Form Games (Game Trees):** A recursive data structure, likely maps with child nodes, will be used to represent decision nodes, branches, and end-state payoffs.

### Core Logic

The calculator's logic will be a set of pure functions that take a game data structure as input and return the analysis results. These functions will be responsible for:
*   Identifying dominant strategies.
*   Finding pure-strategy Nash equilibria.
*   Computing subgame perfect equilibria for game trees.

For performance-critical computations, such as matrix manipulations, Clojure's transients or `core.matrix` implementations can be used internally to optimize performance before returning an immutable result.

### Key Libraries

*   **`core.matrix`:** This library provides a high-level, idiomatic API for numerical computing and N-dimensional array programming in Clojure. It allows for pluggable backends like `vectorz-clj` or `Neanderthal` for high-performance matrix operations required in solving complex games.
*   **Scicloj Ecosystem (`noj`, `tech.ml.dataset`):** For more advanced analysis, data visualization, and potentially machine learning approaches to game theory, the Scicloj ecosystem will be considered.

## Building and Running

This project uses Leiningen for dependency management and running tasks.

### Running the application

To run the application, use the following command:

```sh
lein run
```

### Running tests

To run the tests, use the following command:

```sh
lein test
```

### Building an executable jar

To build a standalone executable jar, use the following command:

```sh
lein uberjar
```

The resulting jar file will be located in the `target/uberjar` directory.

## Development Conventions

*   **Testing:** The project uses `clojure.test` for unit testing. Tests are located in the `test` directory.
*   **Code Style:** The code follows standard Clojure conventions.

## Design notes

*  **Clojure's Built-in Data Structures:** These are ideal for representing game data (e.g., matrices, game trees)
due to their immutability, which ensures function purity and simplifies state management.
Maps to define players, strategies, and link them to payoffs.
Vectors/Nested Vectors to represent payoff matrices for normal form games.
*  **core.matrix and Implementations:** This library provides a high-level, idiomatic API for numerical
computing and N-dimensional array programming in Clojure.
It allows for pluggable backends like vectorz-clj or Neanderthal (which leverages native LAPACK functions via Java interop)
for high-performance matrix operations (e.g., matrix multiplication, linear algebra, optimization) required in solving complex games
like finding mixed-strategy Nash equilibria.
*   **Scicloj Ecosystem (noj, tech.ml.dataset):** This community and set of libraries provide a comprehensive stack for data science
and scientific computing in Clojure, which would be useful for more advanced analysis, data visualization, and potentially machine
learning approaches to game theory.

*  **Design Approach:** Data-Oriented and Functional
*  **Data Representation:** Define clear, simple Clojure data structures (maps, vectors) to model different game types.
*  **Normal Form Games:** A data structure (e.g., a map) would hold player information, strategy sets,
and a payoff matrix represented as a nested vector or a core.matrix type.
*  **Extensive Form Games (Game Trees):** A recursive data structure (e.g., maps with child nodes)
would represent the decision nodes, branches, and end-state payoffs.
*  **Pure Functions for Logic:** The calculator's logic would be a set of pure functions that take the game data structure as
input and return the analysis results.Functions for identifying dominant strategies, finding pure-strategy Nash equilibria,
or computing subgame perfect equilibria for game trees.
*  **Leverage Immutability and Transients:** Immutability simplifies concurrent access and reasoning about the data.
For performance-critical, temporary computations (e.g., during matrix manipulation), core.matrix implementations
or Clojure's transients could be used internally to optimize performance before returning an immutable result.