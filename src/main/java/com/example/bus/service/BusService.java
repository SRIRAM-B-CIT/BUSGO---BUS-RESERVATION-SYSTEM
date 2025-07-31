package com.example.bus.service;

import com.example.bus.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(Booking booking) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(booking.getEmail());
        message.setSubject("🎫 Bus Ticket Confirmation - Seat " + booking.getSeatNumber());
        message.setText("Dear " + booking.getName() + ",\n\n"
                + "🎉 Your bus ticket has been confirmed!\n\n"
                + "📋 BOOKING DETAILS:\n"
                + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n"
                + "🚌 Bus: " + booking.getBusName() + "\n"
                + "📍 Route: " + booking.getFrom() + " → " + booking.getTo() + "\n"
                + "📅 Journey Date: " + booking.getJourneyDate() + "\n"
                + "🕐 Departure Time: " + booking.getDepartureTime() + "\n"
                + "💺 Seat Number: " + booking.getSeatNumber() + "\n"
                + "👤 Passenger: " + booking.getName() + " (" + booking.getGender() + ")\n"
                + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n"
                + "📝 IMPORTANT INSTRUCTIONS:\n"
                + "• Please arrive 30 minutes before departure\n"
                + "• Carry a valid ID proof\n"
                + "• Keep this email as your ticket confirmation\n\n"
                + "Thank you for choosing our service!\n"
                + "Have a safe journey! 🛣️\n\n"
                + "Best regards,\n"
                + "Bus Reservation Team");

        mailSender.send(message);
    }
}
