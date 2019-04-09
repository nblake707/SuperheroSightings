<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>

            <nav class="navbar">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span> 
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Superhero Sightings</a>
                    </div>

                    <div class="navbar">
                        <ul class="nav nav-tabs">
                            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerHome">Superpower</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/personHome">Person</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/locationHome">Location</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/organizationHome">Organization</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/sightingHome">Sighting</a></li>
                            <li role="presentation" ><a href="${pageContext.request.contextPath}/login">Login</a></li>
                        </ul>
                    </div>
                        
                        

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </p>
</c:if>
    
    <div class="row">
            <!-- 
                Add a col to hold the summary table - have it take up half the row 
            -->
            <div class="col-md-12">
                <h2>Top 10 Sightings</h2>
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
    </div>

              

                        <!-- Placed at the end of the document so the pages load faster -->
                        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
                        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

                        </body>
                        </html>

