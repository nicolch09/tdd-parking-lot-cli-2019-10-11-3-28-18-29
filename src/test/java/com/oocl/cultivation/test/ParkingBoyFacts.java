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
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();

        //When
        parkingBoy.park(car1);
        parkingBoy.park(car2);
        ParkingTicket ticket = parkingBoy.park(car1);
        Car fetchedCar = parkingBoy.fetch(ticket);

        //Then
        assertSame(fetchedCar, car1);
        assertNotSame(fetchedCar, car2);
    }

    @Test
    void should_no_car_when_parking_boy_did_not_provide_the_ticket_to_customer() {
        //Given
        ParkingTicket parkingTicket1 = new ParkingTicket();
        ParkingTicket parkingTicket2 = new ParkingTicket();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //When
        Car car = new Car();
        parkingTicket1 = parkingBoy.park(car);
        car = parkingBoy.fetch(parkingTicket2);

        //Then
        assertNull(car);
        assertNotNull(parkingTicket1);
    }

    @Test
    void should_no_car_be_fetched_when_customer_gives_an_already_used_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();

        //When
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car1);
        car1 = parkingBoy.fetch(parkingTicket);
        car2 = parkingBoy.fetch(parkingTicket);

        //Then
        assertNull(car2);
        assertNotNull(car1);
    }

    @Test
    void should_not_get_ticket_when_parking_lot_positions_are_full() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car cars = new Car();
        for(int i = 0; i < 9; i++){
            parkingBoy.park(cars);
        }

        //When
        Car finalCar = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(finalCar);

        //Then
        assertNull(parkingTicket);
    }

    @DisplayName("STORY 2:")
    @Test
    void should_get_unrecognized_parking_ticket_when_customer_gives_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket validTicket = parkingBoy.park(car);

        //When
        ParkingTicket invalidTicket = parkingBoy.park(car);
        car = parkingBoy.fetch(invalidTicket);
        String getParkingBoyMessage = parkingBoy.isValidTicket(invalidTicket,parkingLot);

        //Then
        assertSame(getParkingBoyMessage, "Unrecognized parking ticket.");
    }

    @Test
    void should_get_please_provide_your_parking_ticket_when_customer_does_not_provide_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);

        //When
        String parkingMessage = parkingBoy.isValidTicket(null, parkingLot);

        //Then
        assertSame(parkingMessage, "Please provide your parking ticket.");
    }

    @Test
    void should_get_not_enough_position_when_parking_lot_has_no_more_position() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car cars = new Car();
        for(int i = 0; i < 10; i++){
            Car car1 = new Car();
            parkingBoy.park(car1);
        }

        //When
        ParkingTicket parkingTicket = parkingBoy.park(cars);
        parkingLot.checkParkingAvailability(parkingTicket);

        //Then
        assertSame(parkingLot.checkParkingAvailability(parkingTicket), "Not enough position.");
    }
}
