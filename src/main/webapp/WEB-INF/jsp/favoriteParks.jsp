<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title">Favorite Parks</c:param>
	<c:param name="favsActive">active</c:param>
</c:import>

<div class="container shadow-sm rounded px-5 py-3" id="park-container">
	<h5 class="display-4 text-center" style="font-family: Montserrat">Favorite Parks Survey Results</h5>
	<p class="text-dark text-center" style="font-family: Montserrat">We've asked, and you've answered! These are the results for everyone's favorite National Parks!</p>
	<div>
		<form method="POST">
			<div class="d-flex justify-content-end mb-2 form-group row">
				<label class="col-sm-8 col-form-label text-right" for="activityLevelChosen" style="font-family: Montserrat">Choose an Activity Level to Search By</label>
				<div class="col-sm-4">
					<select class="ml-2 form-control" name="activityLevelChosen">
						<option value=""></option>
						<c:forEach var="activityLevel" items="${activityLevelMap}">
							<option value="${activityLevel.key}">${activityLevel.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="d-flex justify-content-end mb-2 form-group row">
				<label class="col-sm-8 col-form-label text-right" for="stateChosen" style="font-family: Montserrat">Choose State to Search By</label>
				<div class="col-sm-4">
					<select class="ml-2 form-control" name="stateChosen">
						<option value=""></option>
						<c:forEach var="state" items="${statesMap}">
							<option value="${state.key}">${state.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="d-flex justify-content-end">
				<button class="btn btn-outline-primary mb-3" type="submit">Search</button>
			</div>
		</form>
	</div>
	<div>
		<c:if test="${activityLevel ne '' || state ne ''}">
			<h5 class="text-center" style="font-family: Overpass;">Searching...</h5>
		</c:if>
		<c:if test="${activityLevel ne ''}">
			<h6 class="text-center" style="font-family: Overpass;">Activity Level: ${activityLevelMap.get(activityLevel)}</h6>
		</c:if>
		<c:if test="${state ne ''}">
			<h6 class="text-center" style="font-family: Overpass;">State Chosen: ${statesMap.get(state)}</h6>
		</c:if>
	</div>
	<div class="row justify-content-center">
		<c:forEach var="survey" items="${surveys}">
			<c:if test="${survey.value > 0}">
				<div class="col-sm-auto col-lg-2 bg-light m-2 py-2 rounded shadow">
					<c:url var="parkImg" value="img/parks/${survey.key.parkCode.toLowerCase()}.jpg" />
					<img class="card-img park-image shadow " src="${parkImg}" alt="${survey.key.parkName} image">
					<p class="mt-2 h6 text-center">${survey.key.parkName}</p>
					<p class="text-center">${survey.value}${survey.value == 1 ? ' person' : ' people'} voted for this park!</p>
				</div>
			</c:if>
		</c:forEach>
	</div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

