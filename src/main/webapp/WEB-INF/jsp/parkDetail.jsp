<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title"></c:param>
</c:import>

<div class="container shadow-sm rounded px-5 py-3" id="park-container">
	<c:url var="parkImg" value="img/parks/${park.parkCode.toLowerCase()}.jpg" />
	<img class="img-fluid mx-auto d-block shadow-lg p-1 mb-2 rounded bg-white" src="${parkImg}" alt="${park.parkName} image">
	
	<div id="park-info">
		<div class="mt-3 d-flex align-items-end flex-column" style="font-family: Montserrat">
			<h3 class="display-4">${park.parkName}</h3>
			<h5 class="align-self-end">${park.state}</h5>
		</div>
		<div style="font-family: Overpass">
			<blockquote class="blockquote text-right">
				<p class="mb-0">${park.inspirationalQuote}</p>
				<footer class="blockquote-footer">${park.inspirationalQuoteSource}</footer>
			</blockquote>
			<hr>
			
			<div class="row mb-4 bg-white rounded p-2 shadow">
				<div class="col-5">
					<p class="text-dark">${park.description}</p>
				</div>
				
				<div class="col-3 align-self-center">
					<p class="text-muted"><strong class="text-dark">Entry Fee:</strong> <fmt:formatNumber value="${park.entryFee}" type="currency" /></p>
					<p class="text-muted"><strong class="text-dark">Acreage:</strong> <fmt:formatNumber value="${park.acreage}" /></p>
					<p class="text-muted"><strong class="text-dark">Elevation:</strong> <fmt:formatNumber value="${park.elevationInFeet}" /> ft</p>
					<p class="text-muted"><strong class="text-dark">Miles of Trails:</strong> ${park.milesOfTrail}</p>
					<p class="text-muted"><strong class="text-dark">Campsites:</strong> ${park.campsites}</p>
				</div>
				<div class="col-4 align-self-center">
					<p class="text-muted"><strong class="text-dark">Climate:</strong> ${park.climate}</p>
					<p class="text-muted"><strong class="text-dark">Year Founded:</strong> ${park.yearFounded}</p>
					<p class="text-muted"><strong class="text-dark">Annual Visitor Count:</strong> <fmt:formatNumber value="${park.annualVisitorCount}" /></p>
					<p class="text-muted"><strong class="text-dark">Num. of Animal Species:</strong> ${park.numberOfAnimalSpecies}</p>
				 </div>
			 </div>
			 <hr>
			<form method="POST" id="weather">
			 <div class="d-flex justify-content-end">
			 	<button class="btn btn-outline-primary mb-3" name="tempScaleChange" type="submit"
			 	value="${sessionScope.tempScale == 'F' ? 'C' : 'F'}">
			 	Change Temps to °${sessionScope.tempScale == 'F' ? 'C' : 'F'}</button>
			</div>
			</form>
			 
			 <div class="card-group justify-content-center pb-3">
			 <c:forEach var="weatherForecast" items="${weather}">
				<div class="card weather-card shadow-sm rounded">
					<c:url var="forecastImg" value="/img/weather/${weatherForecast.forecast}.png" />
					<img class="card-img-top" src="${forecastImg}" alt="${weatherForecast.forecast} image">
					<div class="card-body">
						<h5 class="card-title">${weatherForecast.day}</h5>
						
						<c:if test="${sessionScope.tempScale == 'F'}">
							<h5 class="card-text"><fmt:formatNumber value="${weatherForecast.highTempF}" maxFractionDigits="2" /> °F / <span class="h6"><fmt:formatNumber value="${weatherForecast.lowTempF}" maxFractionDigits="2" /> °F</span></h5>
						</c:if>
						<c:if test="${sessionScope.tempScale == 'C'}">
							<h5 class="card-text"><fmt:formatNumber value="${weatherForecast.highTempC}" maxFractionDigits="2" /> °C / <span class="h6"><fmt:formatNumber value="${weatherForecast.lowTempC}" maxFractionDigits="2" /> °C</span></h5>
						</c:if>
						<p class="card-text">${weatherForecast.weatherAdvisory}<c:if test="${weatherForecast.weatherAdvisory ne ''}"><br><br></c:if>${weatherForecast.tempAdvisory}</p>
					</div>
				</div>
			 </c:forEach>
			 </div>
		</div>
	</div>
	<hr>
	<div class="pt-2 d-flex justify-content-center">
		<div class="mapouter">
			<div class="map-responsive">
				<iframe src="https://maps.google.com/maps?q=${park.parkName}&t=&z=9&ie=UTF8&iwloc=&output=embed"></iframe>
			</div>
		</div>
	</div>


</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />