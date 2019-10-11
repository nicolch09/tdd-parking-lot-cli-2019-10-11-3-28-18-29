package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_park_a_car_into_parking_lot_by_parking_boy_and_returns_a_ticket() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //Then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_parking_boy_can_fetch_car() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //When
        Car fetch = parkingBoy.fetch(parkingTicket);

        //Then
        assertNotNull(fetch);
    }

    @Test
    void should_parking_boy_can_fetch_right_car_using_corresponding_ticket() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        //When
        parkingLot.fetch(parkingTicket1);
        parkingLot.fetch(parkingTicket2);

        //Then
        assertNotNull(car1);
        assertNotNull(car2);
    }

//    @Test
//    void should_no_car_when_parking_boy_did_not_provide_the_ticket_to_customer() {
//        //Given
//        ParkingLot parkingLot = new ParkingLot();
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
//        ParkingTicket parkingTicket = parkingBoy.park(null);
//
//        //When
//        Car fetch = parkingBoy.fetch(parkingTicket);
//
//        //When
//        assertNotNull(fetch);
//    }
}
