
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
          <title>Person Details</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
</head>
<body>
    <div class="container">

        <h1>Person Details</h1>
        <hr/>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/">
                        Home
                    </a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/displayLocationDetails">
                        Person Details
                    </a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/displayEditLocationForm">
                        Edit Person
                    </a>
                </li>
            </ul>    
        </div>

        <p>
            Person First Name:<c:out value ="${person.firstName}"/>
        </p>


        <p>Person Last Name:
            <c:out value ="${person.lastName}"/>
        </p>


        <p>Is Hero :
            <c:out value ="${person.isHero}"/>
        </p>

        <p>Person Description:
            <c:out value ="${person.descriptionOfPerson}"/>
        </p>

       

        <div class="col-md-8">
             <h3>Superpowers</h3>
        
            <table id="superpowerTable" class="table table-hover">

                <tr>
                    <th width="50%">Superpower Name</th>
                    <th width="50%">Superpower Description</th>
                </tr>
                <c:forEach var="currentSuperpower" items="${personSuperpowers}">
                    <tr>
                        <td>
                            <c:out value=" ${currentSuperpower.superpowerName}"/>
                        </td>

                        <td>
                            <c:out value="${currentSuperpower.superpowerDescription}"/>
                        </td>  

                    </tr>
                </c:forEach>
            </table>
        </div>


        <div class="col-md-8">
            <h3>
                Organizations
            </h3>
            <table id="superpowerTable" class="table table-hover">

                <tr>
                    <th width="50%">Organization Name</th>
                    <th width="50%">Organization Description</th>
                </tr>
                <c:forEach var="currentOrganization" items="${personOrganizations}">
                    <tr>
                        <td>
                            <c:out value="${currentOrganization.organizationName}"/>
                        </td>

                        <td>
                            <c:out value=" ${currentOrganization.organizationDescription}"/>
                        </td>  

                    </tr>
                </c:forEach>
            </table>
        </div>


    </div>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
