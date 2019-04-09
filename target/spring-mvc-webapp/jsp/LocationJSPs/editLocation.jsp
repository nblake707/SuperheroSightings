<%-- 
    Document   : editLocation
    Created on : Jan 16, 2019, 2:30:52 PM
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
        <title>Edit Location</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>
        <hr/>
           <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerHome">Superpower</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/personHome">Person</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/locationHome">Location</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/organizationHome">Organization</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/sightingHome">Sighting</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/userHome">User</a></li>
                </ul>
            </div>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </p>
</c:if>

        <div class="container">
            <h1>Edit Location</h1>
            <hr/>  
        </div>


        <sf:form class="form-horizontal" role="form" modelAttribute="locationForEdit"
                 action="editLocation" method="POST">
            <div class="form-group">
                <label for="add-locationName" class="col-md-4 control-label">Location Name:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-locatioName"
                              path="locationName" placeholder="Location Name"/>
                    <sf:errors path="locationName" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-locationDescription" class="col-md-4 control-label">Location Description:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-locationDescription"
                              path="locationDescription" placeholder="Location Description"/>
                    <sf:errors path="locationDescription" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-locationCountry" class="col-md-4 control-label">Location Country:</label>                          
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-locationCountry"
                              path="locationCountry" placeholder="Location Country"/>
                    <sf:errors path="locationCountry" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-locationState" class="col-md-4 control-label">Location State:</label>
                    <div class="col-md-8">
                    <sf:input type="locationState" class="form-control" id="add-locationState"
                              path="locationState" placeholder="Location State"/>
                    <sf:errors path="locationState" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-locationCity" class="col-md-4 control-label">Location City:</label>
                    <div class="col-md-8">
                    <sf:input type="tel" class="form-control" id="add-locationCity"
                              path="locationCity" placeholder="Location City"/>
                    <sf:errors path="locationCity" cssclass="error"></sf:errors>
                    <sf:hidden path="locationId"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-locationStreet" class="col-md-4 control-label">Location Street:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-locationStreet"
                              path="locationStreet" placeholder="Location Street"/>
                    <sf:errors path="locationStreet" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-locationZipCode" class="col-md-4 control-label">Location ZipCode:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-locationZipCode"
                              path="locationZipcode" placeholder="Location ZipCode"/>
                    <sf:errors path="locationZipcode" cssclass="error"></sf:errors>
                    </div>
                </div> 
                <div class="form-group">
                    <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>                          
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-latitude"
                              path="latitude" placeholder="Latitude"/>
                    <sf:errors path="latitude" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                    <div class="col-md-8">
                    <sf:input type="longitude" class="form-control" id="add-longitude"
                              path="longitude" placeholder="Longitude"/>
                    <sf:errors path="longitude" cssclass="error"></sf:errors>
                    </div>
                </div>    

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Location"/>
                    </div>
                </div>
        </sf:form>  



        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>


</html>
