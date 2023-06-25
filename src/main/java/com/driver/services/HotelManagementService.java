package com.driver.services;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repo.HotelManagementRepo;

import java.util.List;

public class HotelManagementService {

    HotelManagementRepo hotelManagementRepo = new HotelManagementRepo();

    public Integer addUser(User user) {
        return hotelManagementRepo.addUser(user);
    }

    public String addHotel(Hotel hotel) {
        return hotelManagementRepo.addHotel(hotel);
    }

    public String getHotelWithMostFacilities() {
        return hotelManagementRepo.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        return hotelManagementRepo.bookARoom(booking);
    }

    public int getBooking(Integer aadharCard) {
        return hotelManagementRepo.getBooking(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hotelManagementRepo.updateFacilities(newFacilities, hotelName);
    }
}