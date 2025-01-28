# Circuit Breaker
- Implement a mechanism that will stop calling the underlying service if it detects there is some downtime

## Requirements
- Failure Rate Threshold percentage of failure that will trigger the breaker
- Two different type of settings:
  - Count-base Sliding window: Given a X size, only the last X calls are recorded.
  - Time-base Sliding window: Given a X size, only the last X seconds are recorded.