<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="./assets/css/login.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            .login-error {
                color: red;
                font-size: 15px;
                margin-top: -18px;
                margin-left: 2px;
            }
        </style>
    </head>

    <body>
        <div class="login">
            <h1 class="login-heading">Login</h1>
            <button class="login-social">
                <!-- <i class="fa fa-google signup-social-icon"></i> -->
                <!-- <i class="fa-brands fa-google"></i> -->

                <span class="login-social-text">
                    <a class="text-decoration-none text-white " " href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8084/BirdFarmShop/login-google&response_type=code
                       &client_id=1070154375273-hib2mbbkpbb47ral7kt3s763c25piprp.apps.googleusercontent.com&approval_prompt=force">Login with Google</a>
                </span>
            </button>
            <button class="login-social-fb ">
                <!-- <i class="fa fa-google signup-social-icon"></i> -->


                <span class="login-social-text">
                    <a class="text-decoration-none text-white " href="https://www.facebook.com/dialog/oauth?client_id=318980067328061&
                       redirect_uri=http://localhost:8084/BirdFarmShop/login-facebook">Login with Facebook</a>
                </span>
            </button>
            <div class="login-or">
                <span>Or</span>
            </div>

            <form action="log-in" class="login-form" autocomplete="off" method="POST">

                <c:set var="err" value="${requestScope.CREATE_ERROR}"/>
                <c:set var="notification" value="${requestScope.NOTIFICATION}"/>

                <label for="email" class="login-label">Email Address</label>
                <input name="txtEmailLogin" value="${param.txtEmailLogin}" type="email" id="email" class="login-input" placeholder="example@gmail.com">
                <c:if test="${not empty err.emptyEmail}">
                    <p class="login-error">${err.emptyEmail}</p>
                </c:if>
                <c:if test="${not empty err.wrongEmail}">
                    <p class="login-error">${err.wrongEmail}</p><br>
                </c:if>

                <label for="password" class="login-label">Password</label>
                <input name="txtPasswordLogin" type="password" class="login-input-pass" placeholder="Eg: 123"><br>
                <c:if test="${not empty err.emptyPassword}">
                    <p class="login-error">${err.emptyPassword}</p><br>
                </c:if>
                <c:if test="${not empty err.wrongPassword}">
                    <p class="login-error">${err.wrongPassword}</p><br>
                </c:if>

                <div class="check-forget">
                    <div class="col-md-6">
                        <div class="checkbox-1">
                            <input type="checkbox" class="login-remember" value="remember" > Remember me ?
                        </div>
                    </div>

                    <div class="col-md-6">
                        <p class="login-forgetpassword">
                            <a href="guest?btAction=forgetPassPage" class="login-forgetpassword-link">Forget password ?</a>
                        </p>
                    </div>
                </div>
                <button class="login-submit">Login</button>
            </form>


            <p class="login-already">
                <span>Haven't a account ?</span>
                <a href="guest?btAction=registerPage" class="signup-login-link">Register</a>
            </p>
        </div>
    </div>

</body>

</html>