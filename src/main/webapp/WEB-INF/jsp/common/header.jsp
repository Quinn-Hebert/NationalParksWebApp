<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>National Park Geek - ${param.title}</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/npgeek.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat|Overpass:300,400,700" rel="stylesheet">
</head>
<body>
	<div class="d-flex justify-content-center py-3" id="header">
		<c:url var="logo" value="/img/logo.png" />
		<c:url var="home" value="/" />
		<a href="${home}"><img src="${logo}" alt="National Park Geek Logo" id="logo"></a>
	</div>
	
	<nav class="navbar navbar-expand-lg navbar-dark text-uppercase">
		<a class="navbar-brand"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item ${param.homeActive}">
					<a class="nav-link" href="${home}">Home</a>
				</li>
						
				<li class="nav-item ${param.surveyActive}">
					<c:url var="survey" value="/survey" /> 
					<a class="nav-link" href="${survey}">Survey</a>
				</li>
				
				<li class="nav item ${param.favsActive}">
					<c:url var="favParks" value="/favoriteParks" />
					<a class="nav-link" href="${favParks}">Favorite Parks</a>
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="py-3" id="main-content">
		<div class="container">