<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Organization</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>
        <hr/>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerHome">Superpower</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/personHome">Person</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/locationHome">Location</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/organizationHome">Organization</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/sightingHome">Sighting</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/userHome">Login</a></li>
            </ul>
        </div>
            
            
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </p>
</c:if>  
            
            
        <div class="container">
            <h1>Edit Sighting</h1>
            <hr/>   
            <sf:form class="form-horizontal" role="form" modelAttribute="sighting"
                     action="editSighting" method="POST">
                <sf:hidden path="sightingId"></sf:hidden>

                <div class="form-group">
                    <label for="add-IsHero" class="col-md-4 control-label">Is this a hero:</label>
                    <div class="col-md-8">

                        <label><input type="radio" class="form-control" value="true" name="IsHero" placeholder="true"/>Yes</label>
                        <label><input type="radio" class="form-control" value="false" name="IsHero" placeholder="false"/>No</label>

                    </div>
                </div> 
<!--                
                <div class="form-group">
                <label for="add-last-name" class="col-md-4 control-label">Sighting Date:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="sightingDate" placeholder="sightingDate"/>
                </div>
            </div>-->
                
                <div class="form-group">
                    <label for="add-sightingDate" class="col-md-4 control-label">Sighting Date</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-sighting-Date"
                                  path="justTheSightingDate" placeholder="Sighting Date"/>
                        <sf:errors path="justTheSightingDate" cssclass="error"></sf:errors>
                        </div>
                    </div>

               
<!--               Dropdown for people-->

                    <div class="form-group">
                        <label for="add-sighting-person" class="col-md-4 control-label">People:</label>
                        <select name="people" multiple ="true">
                        <c:forEach var="currentPerson" items="${allPersons}"> 
                            <option value ="${currentPerson.personId}"> ${currentPerson.firstName} ${currentPerson.lastName} </option>
                        </c:forEach>
                    </select>
                </div> 

<!--                        dropdown for locations-->
                        
                <div class="form-group">
                    <label for="add-sighting-location" class="col-md-4 control-label">Locations:</label>
                    <select name="locations" multiple ="true">
                        <c:forEach var="currentLocation" items="${allLocations}"> 
                            <option value ="${currentLocation.locationId}"> ${currentLocation.locationName} </option>
                        </c:forEach>
                    </select>
                </div>
                        
                     <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Sighting"/>
                    </div>
                </div>
            </sf:form>

        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>        

    </body>
</html>
