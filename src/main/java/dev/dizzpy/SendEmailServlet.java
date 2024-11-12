package dev.dizzpy;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@WebServlet(name = "SendEmailServlet", urlPatterns = {"/SendEmailServlet"})
public class SendEmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String subject = "";
        String messageContent = "";

        if ("reserve".equals(action)) {
            subject = "ABC Cinema - Reservation Confirmation";
            messageContent = "Dear Dizzpy,\n\nYour reservation for Inception has been successfully confirmed!\n\n" +
                    "Show Time: [Date and Time]\nSeats: 069\n\n" +
                    "Thank you for choosing ABC Cinema! We look forward to seeing you at the movie.\n\n" +
                    "Best Regards,\nABC Cinema Team";
        } else if ("cancel".equals(action)) {
            subject = "ABC Cinema - Reservation Cancellation";
            messageContent = "Dear Dizzpy,\n\nWe're sorry to inform you that your reservation for Inception has been cancelled as per your request.\n\n" +
                    "Show Time: [Date and Time]\nSeats: 069\n\n" +
                    "If this was not intended, please contact our support team immediately.\n\n" +
                    "Best Regards,\nABC Cinema Team";
        }

        try {
            sendEmail(subject, messageContent);
            if ("reserve".equals(action)) {
                response.sendRedirect("reservationSuccess.jsp");
            } else {
                response.sendRedirect("cancelSuccess.jsp");
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            response.getWriter().write("Error: Email sending failed.");
        }
    }

    private void sendEmail(String subject, String messageContent) throws MessagingException, UnsupportedEncodingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String username = "";
        final String password = "";

        // Get the email session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a message
        Message message = new MimeMessage(session);
        // Set the custom "From" address and name
        message.setFrom(new InternetAddress("contact@abcmovies.com", "ABC Cinema"));
        // Set recipient
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("anujanisal19@gmail.com")); // recipient email
        // Set subject and content
        message.setSubject(subject);
        message.setText(messageContent);

        // Set reply-to address
        message.setReplyTo(InternetAddress.parse("support@abcmovies.com"));

        // Send the message
        Transport.send(message);
    }
}
