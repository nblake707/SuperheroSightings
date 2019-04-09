<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Superpower Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <hr/>

        <h1>Superpower Details</h1>
        <hr/>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/index.jsp">
                        Home
                    </a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/superpowerHome">
                        Superpower 
                    </a>
                </li>
                <li role="presentation">
                    <a href="${pageContext.request.contextPath}/displayEditSuperpowerForm">
                        Edit Superpower
                    </a>
                </li>
            </ul>    
        </div>


        <h3>
            Superpower
        </h3>
    <div class="col-md-8">
        <table id="superpowerTable" class="table table-hover">

            <tr>
                <th width="50%">Superpower Name</th>
                <th width="50%">Superpower Description</th>

            </tr>


            <tr>

                <td>
                    ${superpowerDetails.superpowerName}
                </td>

                <td>
                    ${superpowerDetails.superpowerDescription}
                </td>  

            </tr>

        </table>
      </div>


        <div class="col-md-8">
            <h3>
                Persons That Have This Superpower
            </h3>


            <table id="personsSuperpowerTable" class="table table-hover">

                <tr>
                    <th width="25%">Person Name</th>
                    <th width="25%">Person Type</th>
                    <th width="50%">Person Description</th>

                </tr>


                <c:forEach var = "currentPerson" items = "${personsWithSuperpower}">
                    <tr>

                        <td>
                    <c:out value="${currentPerson.firstName} ${currentPerson.lastName}"/>
                    </td>



                    <td>
                    <c:out value="${currentPerson.isHero}"/>
                    </td>  



                    <td>
                    <c:out value="${currentPerson.descriptionOfPerson}"/>
                    </td>  


                    </tr>

                </c:forEach>
            </table>      

        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>                 


    </body>
</html>
