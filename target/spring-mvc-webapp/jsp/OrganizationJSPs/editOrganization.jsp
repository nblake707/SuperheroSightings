
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
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">    
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
            <h1>Edit Organization</h1>
            <hr/>  

            <!--     The model attribute will contain the key name of the organization object placed on the
     model by the /displayEditOrganizationForm in the controller-->

            <sf:form class="form-horizontal" role="form" modelAttribute="organization"
                     action="editOrganization" method="POST">
                <sf:hidden path="organizationId"></sf:hidden>
                <div class="form-group">
                    <label for="add-organization-name" class="col-md-4 control-label">Organization Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-organization-name" 
                                  path="organizationName" placeholder="Organization Name"/>
                        <sf:errors path="organizationName" cssclass="error"></sf:errors>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-organization-description" class="col-md-4 control-label">Organization Description::</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-organization-description" 
                                  path="organizationDescription" placeholder="Organization Description"/>
                        <sf:errors path="organizationDescription" cssclass="error"></sf:errors>
                        </div>        
                    </div>

                    <div class="form-group">
                        <label for="add-location-country" class="col-md-4 control-label">Organization Country:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-location-country" 
                                  path="organizationCountry" placeholder="Organization Country"/>
                        <sf:errors path="organizationCountry" cssclass="error"></sf:errors>
                        </div>
                    </div>           

                    <div class="form-group">
                        <label for="add-organization-state" class="col-md-4 control-label">Organization State:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-organization-state"
                                  path="organizationState" placeholder="Organization State"/>
                        <sf:errors path="organizationState" cssclass="error"></sf:errors>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="add-organization-city" class="col-md-4 control-label">Organization City :</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-organization-city" 
                                  path="organizationCity" placeholder="Organization City"/>
                        <sf:errors path="organizationCity" cssclass="error"></sf:errors>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-organization-city" class="col-md-4 control-label">Organization Street :</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-organization-city" 
                                  path="organizationStreet" placeholder="Organization Street"/>
                        <sf:errors path="organizationStreet" cssclass="error"></sf:errors>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-organization-zipcode" class="col-md-4 control-label">Organization ZipCode :</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-organization-zipcode" 
                                  path="organizationZipcode" placeholder="Organization ZipCode"/>
                        <sf:errors path="organizationZipcode" cssclass="error"></sf:errors>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-IsHero" class="col-md-4 control-label">Is this a hero:</label>
                        <div class="col-md-8">

                            <label><input type="radio" class="form-control" value="true" name="isItAHeroOrganization" placeholder="true"/>Yes</label>
                            <label><input type="radio" class="form-control" value="false" name="isItAHeroOrganization" placeholder="false"/>No</label>

                        </div>
                    </div>
                        
               <div class="form-group">
                        <label for="add-personSuperpowers" class="col-md-4 control-label">People:</label>
                        <select name="people" multiple ="true">
                            <c:forEach var="currentPerson" items="${people}"> 
                                <option value ="${currentPerson.personId}"> ${currentPerson.firstName} ${currentPerson.lastName} </option>
                            </c:forEach>
                        </select>
                    </div>                           


                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Organization"/>
                    </div>
                </div>
            </sf:form>
        </div>


    </body>
</html>
