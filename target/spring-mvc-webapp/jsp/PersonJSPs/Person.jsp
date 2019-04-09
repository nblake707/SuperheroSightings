
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
        <title>Person Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
           <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerHome">Superpower</a></li>
                        <li role="presentation" class="active" ><a href="${pageContext.request.contextPath}/personHome">Person</a></li>
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
            <div class="col-md-6">
                <h2>Person</h2>
                <table id="personTable" class="table table-hover">
                      <tr>
                <th width="40%">Person Name</th>
                <th width="30%">Person Description</th>
                <th width="15%">Is this a hero?</th>
               
            </tr>

                    <c:forEach var="currentPerson" items="${personList}">
                        <tr>
                            <td>
                                <a href="displayPersonDetails?personId=${currentPerson.personId}">
                                    <c:out value="${currentPerson.firstName}"/> <c:out value="${currentPerson.lastName}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${currentPerson.descriptionOfPerson}"/>
                            </td>
                            <td>
                                <c:out value="${currentPerson.isHero}"/>
                            </td>
                            <td>
                                 <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                <a href="displayEditPersonForm?personId=${currentPerson.personId}">
                                    Edit
                                </a>
                                 </sec:authorize>
                                 </td>
                            </td>
                            <td>
                                 <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                <a href="deletePerson?personId=${currentPerson.personId}">
                                    Delete
                                </a>
                                 </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                </table>                    
            </div> <!-- End col div -->
            <!-- 
                Add col to hold the new contact form - have it take up the other 
                half of the row
            -->
             <sec:authorize access="hasRole('ROLE_ADMIN')"> 
            <div class="col-md-6">
                <h2>Add New Person</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="addPerson">

                    <div class="form-group">
                        <label for="add-FirstName" class="col-md-4 control-label">First Name:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="firstName" placeholder="First Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-LastName" class="col-md-4 control-label">Last Name:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="lastName" placeholder="Last Name"/>
                        </div>
                    </div> 
                      <div class="form-group">
                <label for="add-IsHero" class="col-md-4 control-label">Is this a hero:</label>
                <div class="col-md-8">

                    <label><input type="radio" class="form-control" value="yes" name="yes" placeholder="yes"/>Yes</label>
                    <label><input type="radio" class="form-control" value="no" name="no" placeholder="no"/>No</label>
                    
                </div>
            </div>
                    <div class="form-group">
                        <label for="add-PersonDescription" class="col-md-4 control-label">Person Description:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="personDescription" placeholder="Person Description"/>
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



                    <!--            ADD BUTTON   -->
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Add Person"/>
                        </div>
                    </div>
                </form>

            </div> <!-- End col div -->
             </sec:authorize>

        </div> <!-- End row div -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
