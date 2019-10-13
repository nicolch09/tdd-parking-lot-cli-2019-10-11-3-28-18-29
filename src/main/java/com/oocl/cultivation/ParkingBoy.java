package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket ticket) {
        return parkingLot.fetch(ticket);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public String isValidTicket(ParkingTicket parkingTicket, ParkingLot parkingLot) {
        if(parkingTicket == null){
            return lastErrorMessage = "Please provide your parking ticket.";
        }
        if(parkingLot.isValidTicket(parkingTicket) == null) {
            return lastErrorMessage = "Unrecognized parking ticket.";
        }
        return "";
    }
}
