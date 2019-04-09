
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
        <title>Edit Superpower</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>
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
            <h1>Edit Superpower</h1>
            <hr/>
        </div>

        <sf:form class="form-horizontal" role="form" modelAttribute="superpowerForEdit"
                 action="editSuperpower" method="POST">
            <sf:hidden path="superpowerId"></sf:hidden>
            
            <div class="form-group">
                <label for="add-superpowerName" class="col-md-4 control-label">Superpower Name:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-superpowerName"
                               path="superpowerName" placeholder="Superpower Name"/>
                    <sf:errors path="superpowerName" cssclass="error"></sf:errors>
                    </div>
                </div>
                    
                <div class="form-group">
                    <label for="add-superpowerDescription" class="col-md-4 control-label">Superpower Description:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-superpowerDescription"
                               path="superpowerDescription" placeholder="Superpower Description"/>
                    <sf:errors path="superpowerDescription" cssclass="error"></sf:errors>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Superpower"/>
                    </div>
                </div>
        </sf:form>                
    </div>




    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
