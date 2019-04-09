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
        <title>Sighting JSP </title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>           
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerHome">Superpower</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/personHome">Person</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/locationHome">Location</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/organizationHome">Organization</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sightingHome">Sighting</a></li>
                <li role="presentation" ><a href="${pageContext.request.contextPath}/login">Login</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                <li role="presentation" ><a href="${pageContext.request.contextPath}/admin">Admin Page</a></li>
                </sec:authorize>
            </ul>
        </div>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Hello : ${pageContext.request.userPrincipal.name}
                | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
            </p>
        </c:if>   


        <!-- 
       Add a row to our container - this will hold the summary table and the new
       contact form.
        -->
        <div class="row">
            <!-- 
                Add a col to hold the summary table - have it take up half the row 
            -->
            <div class="col-md-6">
                <h2>Sighting</h2>
                <table id="sightingTable" class="table table-hover">
                    <tr>
                        <th width="25%">Sighting Date</th>
                        <th width="25%">Person</th>
                        <th width="25%">Location</th>
                        <th width="25%"></th>
                    </tr> 


                    <c:forEach var="currentSighting" items="${Sightings}">
                        <tr>
                            <td>
                                <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                    <c:out value="${currentSighting.sightingDate}"/> 
                                </a>
                            </td>
                            <td>
                                <a href="displayPersonDetails?personId=${currentSighting.person.personId}">
                                    <c:out value="${currentSighting.person.firstName}"/> <c:out value="${currentSighting.person.lastName}"/>
                                </a>
                            </td>

                            <td>
                                <a href="displayLocationDetails?locationId=${currentSighting.locationId}">
                                    <c:out value="${currentSighting.location.locationName}"/> 
                                </a>
                            </td>

                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                    <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}"/>
                                    Edit
                                    </a>
                                </sec:authorize>
                            </td>
                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                    <a href="deleteSighting?sightingId=${currentSighting.sightingId}"/>
                                    Delete
                                    </a>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>     


                </table>
            </div>





         
                <div class="col-md-6">
                    <h2>Add New Sighting</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="addSighting">

                       
                        <div class="form-group">
                            <label for="add-IsHeroSighting " class="col-md-4 control-label">Sighting Type:</label>
                            <div class="col-md-8">
                                <label class="choice" > <input type="radio" name="isHero" value="true"> Superhero </label>
                                <label class="choice" > <input type="radio" name="isHero" value="false"> Super-villain </label>
                            </div>
                        </div> 

                        <div class="form-group">


                            <label for="add-persons" class="col-md-4 control-label">Locations <br>

                            </label>

                            <select name = "locationSelectedByUser" multiple ="true">


                                <c:forEach var="currentLocation" items="${locationList}">

                                    <option value="${currentLocation.locationId}">${currentLocation.locationName} </option>

                                </c:forEach>


                            </select>


                        </div>

                        <div class="form-group">


                            <label for="add-persons" class="col-md-4 control-label">Persons <br>

                            </label>

                            <select name = "personSelectedByUser" multiple ="true">


                                <c:forEach var="currentPerson" items="${personList}">

                                    <option value="${currentPerson.personId}">${currentPerson.firstName} ${currentPerson.lastName}</option>

                                </c:forEach>


                            </select>


                        </div>

                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">Sighting Date:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="sightingDate" placeholder="Sighting Date"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Add Superhero Sighting"/>
                            </div>
                        </div>
                    </form>

                </div> <!-- End col div -->
           

        </div> <!-- End row div -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>                 


    </body>
</html>