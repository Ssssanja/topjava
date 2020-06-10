<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<link href="MealsPageStyle.css" text="text/css" rel="stylesheet">
<body style="background-color: rgba(154,183,255,0.4)">
<h3><a href="index.html">Home</a></h3>
<hr>
<table border="1" style="background-color: lightgray">
    <tr style="solid-color: black">
        <td>Description</td>
        <td>Calories</td>
        <td>Date/time</td>
    </tr>
    <c:forEach items="${listOfMeals}" var="mealTo">
        <tr style="color: ${mealTo.excess ? 'green' : 'red'}">
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td>${mealTo.dateTime.format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>