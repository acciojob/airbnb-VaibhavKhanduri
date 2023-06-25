package com.driver.repo;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.awt.print.Book;
import java.util.*;

public class HotelManagementRepo {

    HashMap<String, Hotel> hotelDb = new HashMap<>();
    HashMap<Integer, User> userDb = new HashMap<>();
    HashMap<String, Booking> bookingDb = new HashMap<>();
    HashMap<Integer, List<Booking>> personBookingsList = new HashMap<>();

    public Integer addUser(User user) {
        userDb.put(user.getaadharCardNo(), user);
        return user.getaadharCardNo();
    }

    public String addHotel(Hotel hotel) {
        if(hotel == null || hotel.getHotelName() == null || hotelDb.containsKey(hotel.getHotelName())) {
            return "FAILURE";
        }

        hotelDb.put(hotel.getHotelName(), hotel);
        return "SUCCESS";
    }

    public String getHotelWithMostFacilities() {
        int maxFacilities = -1;
        String hotelName = "";
        for(String hotelName1 : hotelDb.keySet()) {
            int numberOfFacilities = hotelDb.get(hotelName1).getFacilities().size();
            if(numberOfFacilities > maxFacilities) {
                maxFacilities = numberOfFacilities;
                hotelName = hotelName1;
            } else if(numberOfFacilities == maxFacilities) {
                String[] arr = new String[] {hotelName, hotelName1};
                Arrays.sort(arr);
                hotelName = arr[0];
            }
        }
        return maxFacilities <= 0 ? "" : hotelName;
    }

    public int bookARoom(Booking booking) {
        Hotel hotel = hotelDb.get(booking.getHotelName());

        int requiredRooms = booking.getNoOfRooms();
        if(requiredRooms > hotel.getAvailableRooms()) return -1;

        String bookingId = String.valueOf(UUID.randomUUID());

        int pricePerNight = hotel.getPricePerNight();
        int totalPrice = pricePerNight * requiredRooms;

        booking.setBookingId(bookingId);
        booking.setAmountToBePaid(totalPrice);

        bookingDb.put(bookingId, booking);

        int aadharNumber = booking.getBookingAadharCard();
        if(!personBookingsList.containsKey(aadharNumber)) {
            personBookingsList.put(aadharNumber, new ArrayList<>());
        }

        personBookingsList.get(aadharNumber).add(booking);

        return totalPrice;
    }

    public int getBooking(Integer aadharCard) {
        if(!personBookingsList.containsKey(aadharCard)) return 0;
        return personBookingsList.get(aadharCard).size();
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelDb.get(hotelName);
        List<Facility> facilities = hotel.getFacilities();

        HashSet<Facility> set = new HashSet<>(facilities);
        for(Facility facility : newFacilities) {
            if(!set.contains(facility)) {
                facilities.add(facility);
            }
        }
        return hotel;
    }
}