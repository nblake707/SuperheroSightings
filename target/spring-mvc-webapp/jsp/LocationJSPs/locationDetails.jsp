
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
        <title>Location Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        
        <div class="container">
        
            <h1>Location Details</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                  <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayLocationDetails">
                          Location Details
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayEditLocationForm">
                          Edit Location
                      </a>
                  </li>
                </ul>    
            </div>
        
        <p>
            Location Name:<c:out value ="${location.locationName}"/>
        </p>
   
        
        <p>Location Description:
                <c:out value ="${location.locationDescription}"/>
        </p>
        
        
        <p>Location Country:
                <c:out value ="${location.locationCountry}"/>
        </p>
        
        <p>Location State:
                <c:out value ="${location.locationState}"/>
        </p>
        
        <p>Location City:
                <c:out value ="${location.locationCity}"/>
        </p>
        
        <p>Location Street:
                <c:out value ="${location.locationStreet}"/>
        </p>
        
        <p>Location Zipcode:
                <c:out value ="${location.locationZipcode}"/>
        </p>
        
        <p>Latitude:
                <c:out value ="${location.latitude}"/>
        </p>
        
        <p>Longitude:
                <c:out value ="${location.longitude}"/>
        </p>
        </div>
        
        
                <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>
