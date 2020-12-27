### Services

| Service  |  Port |
| ------------ | ------------ |
| account-service  | 8100  |
| auth-service  | 8200  |
| bike-management-service  | 8800  |
| dock-management-service  | 8900  |
| feedback-service  | 8300  |
| payment-calculator-service  | 9000  |
| payment-service  | 8400  |
| rent-service  | 8500  |
| travel-history-service  | 8600  |
| travel-history-process-service  | 8700  |
| payment-manager-service  | 9100  |
| zuul-server  | 8762  |
| eureka-server  | 8761  ||

### Kafka
```cd kafka-docker```

```docker-compose -f docker-compose-expose.yml up```