# Transport tracking application

## Running application locally

```
	git clone https://github.com/vahundos/transport-tracking.git
	cd transport-tracking
	./mvnw spring-boot:run
```

You can then access transport-tracking here: http://localhost:8080/

![Application](/raw/app_1.png)

Trackers positions updates every 5 seconds. Trackers moves randomly with fixed step.
App writes to database (using in memory database (HSQLDB)) when tracker enters or leaves zone. You can see this events in the table, click 'Refresh' for refresh data in table. 

## How to

- To create new tracker choose hand icon on map, than click to place it

![Hand icon](/raw/how_to_hand.png)

- To create new zone choose circle icon on map, than click to place it and move mouse to choose radius and than click agin to apply

![Circle icon](/raw/how_to_circle.png) 

