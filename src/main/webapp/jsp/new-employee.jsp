<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <form action="/employee" method="post"  role="form" data-toggle="validator" >
                <c:if test ="${empty action}">
                    <c:set var="action" value="add"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}">
                <input type="hidden" id="idEmployee" name="idEmployee" value="${employee.id}">
                <h2>従業員</h2>
                <div class="form-group col-xs-4">
                    <label for="lastName" class="control-label col-xs-4">姓:</label>
                    <input type="text" name="lastName" id="lastName" class="form-control" value="${employee.lastName}" required="true"/>

                    <label for="name" class="control-label col-xs-4">名:</label>
                    <input type="text" name="name" id="name" class="form-control" value="${employee.name}" required="true"/>

                    <label for="birthdate" class="control-label col-xs-4">誕生日:</label>
                    <input type="text"  pattern="^\d{2}-\d{2}-\d{4}$" name="birthDate" id="birthdate" class="form-control" value="${employee.birthDate}" maxlength="10" placeholder="dd-MM-yyyy" required="true"/>

                    <label for="role" class="control-label col-xs-4">役職:</label>
                    <input type="text" name="role" id="role" class="form-control" value="${employee.role}" required="true"/>

                    <label for="department" class="control-label col-xs-4">部署:</label>
                    <input type="text" name="department" id="department" class="form-control" value="${employee.department}" required="true"/>

                    <label for="department" class="control-label col-xs-4">E-mail:</label>
                    <input type="text" name="email" id="email" class="form-control" value="${employee.email}" placeholder="smith@aol.com" required="true"/>

                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">承認</button>
                </div>
            </form>
        </div>
    </body>
</html>