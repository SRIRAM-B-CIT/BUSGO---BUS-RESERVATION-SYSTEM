package com.example.bus.controller;

import com.example.bus.model.Booking;
import com.example.bus.service.BusService;
import com.example.bus.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Controller
public class BusController {

    @Autowired
    private BusService busService;
    
    @Autowired
    private SeatService seatService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "index";
    }

    @PostMapping("/book")
    public String submitBooking(@ModelAttribute Booking booking, Model model) {
        
        populateRouteInfo(booking);
        
        
        boolean seatBooked = seatService.bookSeat(booking.getBusName(), booking.getJourneyDate(), booking.getSeatNumber());
        
        if (!seatBooked) {
            model.addAttribute("error", "Sorry, seat " + booking.getSeatNumber() + " is already booked. Please select another seat.");
            model.addAttribute("booking", booking);
            return "index";
        }
        
        busService.sendConfirmationEmail(booking);
        model.addAttribute("message", "Booking confirmed! Seat " + booking.getSeatNumber() + " reserved. Confirmation email sent!");
        model.addAttribute("booking", new Booking()); 
        return "index";
    }
    
    @GetMapping("/seats")
    @ResponseBody
    public ResponseEntity<List<String>> getAvailableSeats(@RequestParam String busName, @RequestParam String date) {
        List<String> availableSeats = seatService.getAvailableSeats(busName, date);
        return ResponseEntity.ok(availableSeats);
    }
    
    @GetMapping("/seats-count")
    @ResponseBody
    public ResponseEntity<Integer> getAvailableSeatsCount(@RequestParam String busName, @RequestParam String date) {
        int count = seatService.getAvailableSeatsCount(busName, date);
        return ResponseEntity.ok(count);
    }
    
    private void populateRouteInfo(Booking booking) {
        switch (booking.getBusName()) {
            case "GreenLine Express":
                booking.setFrom("Chennai");
                booking.setTo("Bangalore");
                booking.setDepartureTime("09:00 AM");
                break;
            case "KSRTC Super Deluxe":
                booking.setFrom("Kochi");
                booking.setTo("Trivandrum");
                booking.setDepartureTime("07:30 AM");
                break;
            case "Night Star Travels":
                booking.setFrom("Hyderabad");
                booking.setTo("Vizag");
                booking.setDepartureTime("10:00 PM");
                break;
            default:
                booking.setFrom("Unknown");
                booking.setTo("Unknown");
                booking.setDepartureTime("TBD");
        }
    }
}
