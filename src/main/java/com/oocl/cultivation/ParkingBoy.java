package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return new ParkingTicket();
    }

    public Car fetch(ParkingTicket ticket) {
        return new Car();
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
