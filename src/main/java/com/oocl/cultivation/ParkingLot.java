package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();
    Car myCar;

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }

    public ParkingTicket park(Car car){
        ParkingTicket parkingTicket = new ParkingTicket();
        cars.put(parkingTicket, car);
        if(getAvailableParkingPosition() < 0){
            return parkingTicket;
        }
        return null;
    }

    public Car fetch(ParkingTicket parkingTicket){
        myCar = cars.get(parkingTicket);
        cars.remove(parkingTicket);
        return myCar;
    }
    public Car checkCar(ParkingTicket ticket) {
        myCar = cars.get(ticket);
        return myCar;
    }

    public String isValidTicket(ParkingTicket ticket) {
        if(checkCar(ticket) == null) {
            return null;
        }
        return "";
    }

    public String checkParkingAvailability(ParkingTicket parkingTicket) {
        if(getAvailableParkingPosition() > 0){
            return "Not enough position.";
        }
        return "";
    }
}
