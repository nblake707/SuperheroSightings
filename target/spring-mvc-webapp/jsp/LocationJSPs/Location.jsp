<%-- 
    Document   : Location
    Created on : Jan 1, 2019, 7:11:41 PM
    Author     : zissah
--%>
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
        <title>Location JSP </title>
        
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>
        
           <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" ><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerHome">Superpower</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/personHome">Person</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/locationHome">Location</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/organizationHome">Organization</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/sightingHome">Sighting</a></li>
                         <li role="presentation" ><a href="${pageContext.request.contextPath}/login/displayLoginPage">Login</a></li>
                </ul>
            </div>
   
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </p>
</c:if>     
                
                
        <div class="row">
     
<!--        Add a col to hold the summary table - have it take up half the row 
    -->
    <div class="col-md-6">
        <h2>Location</h2>
        <table id="locationTable" class="table table-hover">
            <tr>
                <th width="40%">Location Name</th>
                <th width="30%">Location Description</th>
                <th width="15%"></th>
                <th width="15%"></th>
                <th width="15%"></th>
                <th width="15%"></th>
                <th width="15%"></th>
                <th width="15%"></th>
                <th width="15%"></th>
            </tr>
    

    <c:forEach var="currentLocation" items="${locationList}">
       <tr>
           <td>
               <a href="displayLocationDetails?locationId=${currentLocation.locationId}">
               <c:out value="${currentLocation.locationName}"/>
               </a>
           </td>
           <td>
               <c:out value="${currentLocation.locationDescription}"/>
           </td>
           <td>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
               <a href="displayEditLocationForm?locationId=${currentLocation.locationId}">
               Edit
               </a>
                </sec:authorize>
           </td>
           <td>
               <sec:authorize access="hasRole('ROLE_ADMIN')">
               <a href="deleteLocation?locationId=${currentLocation.locationId}">
               Delete
               </a>
               </sec:authorize>
           </td>
       </tr>
   </c:forEach>
       
            </table>                    
    </div>  <!--    End col div -->
    
     
<!--        Add col to hold the new location form - have it take up the other 
        half of the row-->
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        
    <div class="col-md-6">
        <h2>Add New Location</h2>
        <form class="form-horizontal" 
              role="form" method="POST" 
              action="addLocation">
            
            <div class="form-group">
                <label for="addLocationName" class="col-md-4 control-label">Location Name:</label>
                <div class="col-md-8">
                  <input type="text" class="form-control" name="locationName" placeholder="Location Name"/>
                </div>
            </div>
             <div class="form-group">
                <label for="addLocationDescription" class="col-md-4 control-label">Location Description:</label>
                <div class="col-md-8">
                  <input type="text" class="form-control" name="locationDescription" placeholder="Location Description"/>
                </div>
            </div>
            <div class="form-group">
                <label for="addLocationCountry" class="col-md-4 control-label">Location Country:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="locationCountry" placeholder="Location Country"/>
                </div>
            </div>
            <div class="form-group">
                <label for="addLocationState" class="col-md-4 control-label">Location State:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="locationState" placeholder="Location State"/>
                </div>
            </div>
            <div class="form-group">
                <label for="addLocationCity " class="col-md-4 control-label">Location City :</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="locationCity" placeholder="Location City"/>
                </div>
            </div>
            <div class="form-group">
                <label for="addLocationStreet " class="col-md-4 control-label">Location Street :</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="locationStreet" placeholder="Location Street"/>
                </div>
            </div> 
           <div class="form-group">
                <label for="addLocationZipCode " class="col-md-4 control-label">Location ZipCode :</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="locationZipCode" placeholder="Location ZipCode"/>
                </div>
            </div>   
            <div class="form-group">
                <label for="addLatitude " class="col-md-4 control-label">Latitude:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="latitude" placeholder="Location Latitude"/>
                </div>
            </div>
        <div class="form-group">
                <label for="addLongitude " class="col-md-4 control-label">Longitude :</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="longitude" placeholder="Location Longitude"/>
                </div>
            </div>
  
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Add Location"/>
                </div>
            </div>
        </form>

    </div>      
    </sec:authorize>
</div>  
                
                
                        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

