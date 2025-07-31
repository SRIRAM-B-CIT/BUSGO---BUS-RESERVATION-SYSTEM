package com.example.bus.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SeatService {
    
    
    private Map<String, Set<String>> bookedSeats = new ConcurrentHashMap<>();
    private Map<String, Integer> totalSeats = new HashMap<>();
    
    public SeatService() {
        
        totalSeats.put("GreenLine Express", 40);
        totalSeats.put("KSRTC Super Deluxe", 35);
        totalSeats.put("Night Star Travels", 45);
    }
    
    public List<String> getAvailableSeats(String busName, String date) {
        String key = busName + "_" + date;
        Set<String> booked = bookedSeats.getOrDefault(key, new HashSet<>());
        List<String> available = new ArrayList<>();
        
        int total = totalSeats.getOrDefault(busName, 40);
        
        
        for (int row = 1; row <= total/2; row++) {
            String seatA = row + "A";
            String seatB = row + "B";
            
            if (!booked.contains(seatA)) {
                available.add(seatA);
            }
            if (!booked.contains(seatB)) {
                available.add(seatB);
            }
        }
        
        return available;
    }
    
    public boolean bookSeat(String busName, String date, String seatNumber) {
        String key = busName + "_" + date;
        Set<String> booked = bookedSeats.computeIfAbsent(key, k -> new HashSet<>());
        
        if (booked.contains(seatNumber)) {
            return false; 
        }
        
        booked.add(seatNumber);
        return true;
    }
    
    public int getAvailableSeatsCount(String busName, String date) {
        return getAvailableSeats(busName, date).size();
    }
}
