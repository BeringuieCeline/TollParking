# TollParking

Java API to manage a Toll parking.
This library allows you to define your own parking according your pricing policy and uses to check in and check out cars.

##  Getting Started
* JDK 14.0.1
* Maven

## Used Library and plugin
* [JUnit 5](https://junit.org/junit5/)
* [SonarLint 5.1](https://www.sonarlint.org/eclipse/)

## JavaDoc
Access to the JavaDoc by openning the file : ./doc/index.html

## Build
To create the build using Maven:

```
git clone https://github.com/BeringuieCeline/TollParking.git
cd TollParking
mvn clean package
```


## Usage

See ./src/Main.java

A car will be design by a string representing his type as define in the CarType Class:
- CarType.STDCAR : standard parking slot for sedan cars (gasoline-powered)
- CarType.E20CAR : parking slot with 20kw power supply for electric cars
- CarType.E50CAR : parking slot with 50kw power supply for electric cars
	

### Parking creation

Each parking is a class which inherit from Parking class and instantiate is own billing method, according the pricing policy.

```java
protected abstract Double billing(ParkingTicket ticket, LocalDateTime exitTime);
```

You can create your parking directly filled by the constructor :

example :

```java
FixedAmountParking myParking = new  FixedAmountParking(name,newparking);

where : 
	* name: String which designs parking name,
	* newparking is a List<ParkingSlot>
```

or you can create it empty and fill it after:
 
example :

```java
ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
ParkingSlot fkwslot = new ParkingSlot(CarType.E50CAR,"B1");
List<ParkingSlot> myslotsparking = new ArrayList<>(Arrays.asList(stdslot, tkwslot, fkwslot)); 	
FixedAmountParking myparking = new FixedAmountParking (name);
myparking.setParkingSlots(myslotsparking);
			
where : 
	* name: String which designs parking name
```

### Parking usage

#### Check In

When you check in a car, the parking will block the slot and return a ticket which contains:
- check in time
- allocated slot - If no slot available, an error is return: "Parking full: no place available"

```java
ParkingTicket myticket = myparking.checkinParkingSlot(CarType.E20CAR, carEntryTime);

where : 
	* CarType.E20CAR is a string representing the car - define in CarType Class
	* carEntryTime is a LocalDateTime - carEntryTime = LocalDateTime.now()
```

#### Check Out

When you check out a car, the parking will return the bill and free the slot

```java
Double bill  = myparking.checkoutParkingSlot(myticket, carExitTime);

where : 
	* carExitTime is a LocalDateTime - carExitTime = LocalDateTime.now()
```
