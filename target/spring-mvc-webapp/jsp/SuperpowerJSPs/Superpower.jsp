
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
        <title>Superpower</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation" ><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li role="presentation" class="active" ><a href="${pageContext.request.contextPath}/superpowerHome">Superpower</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/personHome">Person</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/locationHome">Location</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/organizationHome">Organization</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/sightingHome">Sighting</a></li>
                <li role="presentation"  ><a href="${pageContext.request.contextPath}/login">Login</a></li>
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
                <h2>Superpowers</h2>
                <table id="superpowerTable" class="table table-hover">
                    <tr>
                        <th width="40%">Superpower Name</th>
                        <th width="30%">Superpower Description</th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                    </tr>

                    <c:forEach var="currentSuperpower" items="${superpowerList}">
                        <tr>
                            <td>
                                <a href="displaySuperpowerDetails?superpowerId=${currentSuperpower.superpowerId}">
                                    <c:out value="${currentSuperpower.superpowerName}"/> 
                                </a>
                            </td>
                            <td>
                                <c:out value="${currentSuperpower.superpowerDescription}"/>
                            </td>
                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                <a href="displayEditSuperpowerForm?superpowerId=${currentSuperpower.superpowerId}">
                                    Edit
                                </a>
                                </sec:authorize>
                            </td>
                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                                <a href="deleteSuperpower?superpowerId=${currentSuperpower.superpowerId}">
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
                    <h2>Add New Superpower</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="addSuperpower">
                        <div class="form-group">
                            <label for="addSuperpowerName" class="col-md-4 control-label">Superpower Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="superpowerName" placeholder="superpower Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="addsuperpowerDescription" class="col-md-4 control-label">Superpower Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="superpowerDescription" placeholder="superpower Description"/>
                            </div>
                        </div> 

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Add Superpower"/>
                            </div>
                        </div>
                         </form>
                </div>
</sec:authorize>

           


        </div> <!-- End col div -->
    
</div> <!-- End row div -->

<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>                 


</body>
</html>
