

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
        <title>Edit Person</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <hr/>
           <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" ><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerHome">Superpower</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/personHome">Person</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/locationHome">Location</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/organizationHome">Organization</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/sightingHome">Sighting</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/login/displayLoginPage">Login</a></li>
                </ul>
            </div>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </p>
</c:if>

        <div class="container">
            <h1>Edit Person</h1>
            <hr/>
<!--     The model attribute will contain the key name of the person object placed on the
     model by the /displayEditPersonForm in the controller-->

            <sf:form class="form-horizontal" role="form" modelAttribute="person"
                     action="editPerson" method="POST">
                <div class="form-group">
                    <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                    <div class="col-md-8">
<!--            
                The path attribute refers to the name of the field on the specified 
                modelAttribute object that should be used to populate this input. -->
                        
                <sf:input type="text" class="form-control" id="add-first-name"
                                  path="firstName" placeholder="First Name"/>
                        <sf:errors path="firstName" cssclass="error"></sf:errors>
                    </div>
                </div>
                    
                <div class="form-group">
                    <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-last-name"
                                  path="lastName" placeholder="Last Name"/>
                        <sf:errors path="lastName" cssclass="error"></sf:errors>
                    </div>
                </div>
                    
                  <div class="form-group">
                <label for="add-IsHero" class="col-md-4 control-label">Is this a hero:</label>
                <div class="col-md-8">

                    <label><input type="radio" class="form-control" value="true" name="IsHero" placeholder="true"/>Yes</label>
                    <label><input type="radio" class="form-control" value="false" name="IsHero" placeholder="false"/>No</label>
                    
                </div>
            </div>
                    
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description</label>                          
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-description"
                                  path="descriptionOfPerson" placeholder="Description"/>
                        <sf:errors path="descriptionOfPerson" cssclass="error"></sf:errors>
                         <sf:hidden path="personId"/>
                    </div>
                </div>
                    
                         <!--            DROP DOWN MENU FOR Superpowers-->
                    <div class="form-group">
                        <label for="add-personSuperpowers" class="col-md-4 control-label">Superpowers:</label>
                        <select name="personSuperpowers" multiple ="true">
                            <c:forEach var="currentSuperpower" items="${superpowerList}"> 
                                <option value ="${currentSuperpower.superpowerId}">${currentSuperpower.superpowerName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!--             DROP DOWN MENU FOR Organizations-->
                    <div class="form-group">
                        <label for="add-organizations" class="col-md-4 control-label"> Organizations: </label>
                        <select name="personOrganizations" multiple ="true">
                            <c:forEach var="currentOrganization" items="${organizationList}"> 
                                <option value ="${currentOrganization.organizationId}">${currentOrganization.organizationName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
     
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Person"/>
                    </div>
                </div>
             </sf:form>               
        </div>



        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>


</html>
