<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title">Daily Survey</c:param>
	<c:param name="surveyActive">active</c:param>
</c:import>
<div class="container shadow-sm rounded px-5 py-3" id="park-container">
	<h2 class="text-center" style="font-family: Montserrat">Please take Survey below</h2>
	<h4 class="text-center" style="font-family: Montserrat">Let us know which National Park is your favorite!</h4>
	<div class="d-flex p-3">
		<form:form action="survey" method="POST" modelAttribute="survey">
			<div class="form-group row justify-content-between">
				<label class="lead col-auto col-form-label" for="parkCode" style="font-family: Overpass">Choose a Park</label>
				<div class="ml-md-auto col-md-auto d-flex align-self-center">
					<form:select cssClass="form-control form-control-sm" path="parkCode" required="required">
						<option value=""></option>
						<c:forEach var="park" items="${parksList}">
							<option value="${park.parkCode}">${park.parkName}</option>
						</c:forEach>
					</form:select>
					<form:errors path="parkCode" />
				</div>
			</div>
			<div class="form-group row">
				<label class="lead col-auto col-form-label" for="email" style="font-family: Overpass">Email</label>
				<div class="ml-md-auto col-md-auto d-flex align-self-center">
					<form:input cssClass="form-control form-control-sm" type="email" path="email" pattern="^.+@.+\..{2,7}" required="required" />
					<form:errors path="emailValid" />
				</div>
			</div>
			<div class="form-group row">
				<label class="lead col-auto col-form-label" for="activityLevel" style="font-family: Overpass">Choose an Activity Level</label>
				<div class="ml-md-auto col-md-auto d-flex align-self-center">
					<form:select cssClass="form-control form-control-sm" path="activityLevel" required="required">
						<option value=""></option>
						<c:forEach var="activityLevel" items="${activityLevelMap}">
							<option value="${activityLevel.key}">${activityLevel.value}</option>
						</c:forEach>
					</form:select>
					<form:errors path="activityLevel" />
				</div>
			</div>
			<div class="form-group row">
				<label class="lead col-md-auto col-form-label" for="state" style="font-family: Overpass;">What is your state of residence?</label>
				<div class="ml-md-auto col-md-auto d-flex align-self-center ">
					<form:select cssClass="form-control form-control-sm" path="state" required="required">
						<option value=""></option>
						<c:forEach var="state" items="${statesMap}">
							<option value="${state.key}">${state.value}</option>
						</c:forEach>
					</form:select>
					<form:errors path="state" />
				</div>
			</div>
			<div class="d-flex justify-content-end">
			<button class="btn btn-outline-primary" type="submit">Submit</button>
			</div>
		</form:form>
	</div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />