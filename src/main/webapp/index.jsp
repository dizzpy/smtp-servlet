<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Email Notification</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
<h2 class="mt-4">Movie Ticket Booking</h2>
<div class="mt-4">
    <form action="SendEmailServlet" method="post">
        <input type="hidden" name="action" value="reserve">
        <button type="submit" class="btn btn-primary">Reserve Ticket</button>
    </form>
    <form action="SendEmailServlet" method="post" class="mt-2">
        <input type="hidden" name="action" value="cancel">
        <button type="submit" class="btn btn-danger">Cancel Ticket</button>
    </form>
</div>
</body>
</html>
