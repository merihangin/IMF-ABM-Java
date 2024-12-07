
# Java Simulation Code for Academic Research

This repository contains the Java simulation code used for research purposes in the context of International Monetary Fund (IMF) program negotiations. The simulation models interactions between different actors such as government bureaucrats, IMF staff, and coalition partners to study the acceptance or rejection of conditionality during negotiations.

## Purpose

The code is designed to simulate the dynamics of IMF conditionality negotiations. It explores the roles of various actors, including:
- Governments and their coalition partners
- Country bureaucrats
- IMF staff

The simulation generates data on the number of conditionalities accepted or rejected under varying parameters such as:
- View divergence within governments
- Contentiousness of proposed conditionalities
- Concern levels of IMF staff

## Files

This project is organized as a single file:
- **Simulation.java**: Contains the main simulation logic and all supporting classes (e.g., `Agent`, `IMFStaff`, `Government`, etc.) in a single file.

## How to Run

1. **Prerequisites**:
   - Install Java Development Kit (JDK) 8 or above.
   - Ensure that `javac` (Java compiler) and `java` (Java runtime) are available in your PATH.

2. **Compiling the Code**:
   - Open a terminal or command prompt.
   - Navigate to the directory containing the `Simulation.java` file.
   - Compile the code using:
     ```
     javac Simulation.java
     ```

3. **Running the Simulation**:
   - After successful compilation, run the simulation using:
     ```
     java Simulation
     ```

4. **Output**:
   - The program will output the simulation results, including key parameters and the average number of accepted conditionalities.

## Key Parameters

The simulation uses the following parameters:
- `NUM_NEGOTIATED_CONDITIONALITY`: Total number of conditionalities proposed.
- `AVG_VIEW_DIVERGENCE`: Average divergence of views among government partners.
- `AVG_CONTENTIOUSNESS`: Average contentiousness of proposed conditionalities.
- `CONCERN_PROBABILITY`: Probability of concerns being raised by IMF staff.

These parameters can be modified within the `Simulation` class to observe their effects on the outcomes.

## Citation

If you use this code for academic purposes, please cite the related paper:

> [Edited Volume name]  
> [Merih Angin]  
> [Chapter name], [2025]

## License

This code is distributed under the [MIT License](https://opensource.org/licenses/MIT).

---

For questions or feedback, please contact angin.merih@gmail.com.
