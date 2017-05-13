<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <form:form modelAttribute="employee" action="/employee-spring-edit" method="POST" role="form" data-toggle="validator" >
                <form:hidden path="action" />
                <form:hidden path="idEmployee" />
                <h2>従業員</h2>
                <div class="form-group col-xs-4">
                    <label for="lastName" class="control-label col-xs-4">姓:</label>
                    <form:input path="lastName" class="form-control" required="true"/>

                    <label for="name" class="control-label col-xs-4">名:</label>
                    <form:input path="name" class="form-control" required="true"/>

                    <label for="birthDate" class="control-label col-xs-4">誕生日:</label>
                    <form:input path="birthDate" pattern="^\d{4}\d{2}\d{2}$" class="form-control" maxlength="8" placeholder="yyyyMMdd" required="true"/>

                    <label for="role" class="control-label col-xs-4">役職:</label>
                    <form:input path="role" class="form-control" required="true"/>

                    <label for="department" class="control-label col-xs-4">部署:</label>
                    <form:input path="department" class="form-control" required="true"/>

                    <label for="email" class="control-label col-xs-4">E-mail:</label>
                    <form:input path="email" class="form-control" placeholder="smith@aol.com" required="true"/>

                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">承認</button>
                </div>
            </form:form>
        </div>
    </body>
</html>