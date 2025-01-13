# Parking Lot II
## Requirements
- Vehicle different sizes 
  - car = 1 parking spot
  - Limo = 2 parking spots
  - semitrucks = 3 parking spots (consecutive)
- Vehicle park in spots
- Multiple floors
- Payment: Different pricing Free/charge(hour/fixed)
  - Generate ticket on entrance
  - Pay on exit on the number of hours
  - If full reject
- Assign spot:
  - Next available spot in the lowest floor possible

## Classes
- Vehicle
  - Car/Limo/Semitruck
  - spotSize = 1,2,3
  - License Plate
- Spot
  - Available
  - Vehicle
- Floor
  - List of spots
- Ticket
  - Start time
  - End time
  - Vehicle
- PaymentSystem
  - List of tickets
  - Rates
- Garage
    - List of Floor
    - PaymentSystem