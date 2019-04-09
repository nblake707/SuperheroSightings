
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
        <title>Organization</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation" ><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerHome">Superpower</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/personHome">Person</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/locationHome">Location</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/organizationHome">Organization</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/sightingHome">Sighting</a></li>
                <li role="presentation"  ><a href="${pageContext.request.contextPath}/login">Login</a></li>
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
                <h2>Organization</h2>
                <table id="organizationTable" class="table table-hover">
                    <tr>
                        <th width="40%">Organization Name</th>
                        <th width="30%">Organization Description</th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                    </tr>


                    <c:forEach var="currentOrganization" items="${organizationList}">
                        <tr>
                            <td>
                                <a href="displayOrganizationDetails?organizationId=${currentOrganization.organizationId}">
                                <c:out value="${currentOrganization.organizationName}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${currentOrganization.organizationDescription}"/>
                            </td>
                            <td>
                                 <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                <a href="displayEditOrganizationForm?organizationId=${currentOrganization.organizationId}">
                                    Edit
                                </a>
                                 </sec:authorize>
                            </td>
                            <td>
                                 <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                <a href="deleteOrganizatio?organizationId=${currentOrganization.organizationId}">
                                    Delete
                                </a>
                                 </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>

                </table>                    
            </div>  <!--    End col div -->


            <!--"action" lets us know which controller endpoint to go to-->
             <sec:authorize access="hasRole('ROLE_ADMIN')"> 
            <div class="col-md-6">
                <h2>Add New Organization</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="addOrganization"> 

                    <div class="form-group">
                        <label for="add-OrganizationName" class="col-md-4 control-label">Organization Name:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="organizationName" placeholder="Organization Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-OrganizationDescription" class="col-md-4 control-label">Organization Description:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="organizationDescription" placeholder="Organization Description"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-OrganizationCountry" class="col-md-4 control-label">Organization Country:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="organizationCountry" placeholder="Organization Country"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-OrganizationState" class="col-md-4 control-label">Organization State:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="organizationState" placeholder="Organization State"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-OrganizationCity " class="col-md-4 control-label">Organization City:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="organizationCity" placeholder="Organization City"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-OrganizationStreet " class="col-md-4 control-label">Organization Street:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="organizationStreet" placeholder="Organization Street"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-OrganizationZipCode " class="col-md-4 control-label">Organization ZipCode:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="organizationZipCode" placeholder="Organization ZipCode"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add-IsHeroOrganization " class="col-md-4 control-label">Organization Type:</label>
                        <div class="col-md-8">
                            <label class="choice" > <input type="radio" name="isHero" value="true"> Superhero </label>
                            <label class="choice" > <input type="radio" name="isHero" value="false"> Super-villain </label>
                        </div>
                        </div> 

                        <div class="form-group">


                            <label for="add-persons" class="col-md-4 control-label">Persons <br>

                                </label>

                            <select name = "personsSelectedByUser" multiple ="true">


                                <c:forEach var="currentPerson" items="${personList}">

                                    <option value="${currentPerson.personId}">${currentPerson.firstName} ${currentPerson.lastName}</option>

                                </c:forEach>


                            </select>


                        </div>


                           <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Add Organization"/>
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
