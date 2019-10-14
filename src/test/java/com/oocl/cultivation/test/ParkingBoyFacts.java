package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {

    @DisplayName("STORY 1:")
    @Test
    void should_park_a_car_into_parking_lot_by_parking_boy_and_returns_a_ticket() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_parking_boy_can_fetch_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetch = parkingBoy.fetch(parkingTicket);

        assertNotNull(fetch);
    }

    @Test
    void should_parking_boy_can_fetch_right_car_using_corresponding_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();

        parkingBoy.park(car1);
        parkingBoy.park(car2);
        ParkingTicket ticket = parkingBoy.park(car1);
        Car fetchedCar = parkingBoy.fetch(ticket);

        assertSame(fetchedCar, car1);
        assertNotSame(fetchedCar, car2);
    }

    @Test
    void should_no_car_when_parking_boy_did_not_provide_the_ticket_to_customer() {
        ParkingTicket parkingTicket1 = new ParkingTicket();
        ParkingTicket parkingTicket2 = new ParkingTicket();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        parkingTicket1 = parkingBoy.park(car);
        car = parkingBoy.fetch(parkingTicket2);

        assertNull(car);
        assertNotNull(parkingTicket1);
    }

    @Test
    void should_no_car_be_fetched_when_customer_gives_an_already_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car1);
        car1 = parkingBoy.fetch(parkingTicket);
        car2 = parkingBoy.fetch(parkingTicket);

        assertNull(car2);
        assertNotNull(car1);
    }

    @Test
    void should_not_get_ticket_when_parking_lot_positions_are_full() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car cars = new Car();
        for(int i = 0; i < 9; i++){
            parkingBoy.park(cars);
        }

        Car finalCar = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(finalCar);

        //Then
        assertNull(parkingTicket);
    }

    @DisplayName("STORY 2:")
    @Test
    void should_get_unrecognized_parking_ticket_when_customer_gives_wrong_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket validTicket = parkingBoy.park(car);

        ParkingTicket invalidTicket = parkingBoy.park(car);
        car = parkingBoy.fetch(invalidTicket);
        String getParkingBoyMessage = parkingBoy.isValidTicket(invalidTicket,parkingLot);

        assertSame(getParkingBoyMessage, "Unrecognized parking ticket.");
    }

    @Test
    void should_get_please_provide_your_parking_ticket_when_customer_does_not_provide_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);

        String parkingMessage = parkingBoy.isValidTicket(null, parkingLot);

        assertSame(parkingMessage, "Please provide your parking ticket.");
    }

    @Test
    void should_get_not_enough_position_when_parking_lot_has_no_more_position() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car cars = new Car();
        for(int i = 0; i < 10; i++){
            Car car1 = new Car();
            parkingBoy.park(car1);
        }

        ParkingTicket parkingTicket = parkingBoy.park(cars);
        parkingLot.checkParkingAvailability(parkingTicket);

        assertSame(parkingLot.checkParkingAvailability(parkingTicket), "Not enough position.");
    }
}
