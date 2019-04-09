
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
        <title>Organization Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        
        <div class="container">
        
            <h1>Organization Details</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                  <li role="presentation">
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                  </li>
                
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayEditLocationForm">
                          Edit Organization
                      </a>
                  </li>
                </ul>    
            </div>
        
        <p>
            Organization Name:<c:out value ="${organization.organizationName}"/>
        </p>
   
        
        <p>Organization Description:
                <c:out value ="${organization.organizationDescription}"/>
        </p>
        
        
        <p>Location Country:
                <c:out value ="${organization.organizationStreet}"/>
        </p>
        
        <p>Location State:
                <c:out value ="${organization.organizationCity}"/>
        </p>
        
        <p>Location City:
                <c:out value ="${organization.organizationState}"/>
        </p>
        
        <p>Location Street:
                <c:out value ="${organization.organizationZipcode}"/>
        </p>
        
        <p>Location Zipcode:
                <c:out value ="${organization.organizationCountry}"/>
        </p>
        
        <p>Latitude:
                <c:out value ="${organization.isItAHeroOrganization}"/>
        </p>
        
       
        </div>
        
        
                <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>
