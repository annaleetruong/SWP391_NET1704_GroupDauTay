

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Available at shop</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="./assets/css/homePage.css">
        <link rel="stylesheet" href="./assets/css/payment.css">
    </head>
    <body>
        <!-- option1 -->
        <!-- header -->
        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-light"
                 style="background-color: rgb(13,103,128) !important;">
                <div class="col-md-6" style="margin: 0 10px 0 50px ;">
                    <a class="navbar-brand justify-content-center  " style="color: #fff !important;
                       font-size: 1.5em;
                       font-weight: 700;
                       text-transform: uppercase;
                       text-decoration: none;
                       padding-right: 10px;" href="home">Bird farm shop</a>
                </div>
                <div class="col-md-5">
                    <div class="collapse navbar-collapse" id="navbarText">
                        <ul class="navbar-nav mr-auto ">
                            <li class="nav-item active align-content-lg-end" style="display: flex;">
                                <a class="nav-link" style="color: #ffc107 ;margin: auto; align-items: center;"
                                   href="#">Login </a>
                            </li>
                            <li class="nav-item active align-content-lg-end material-symbols-outlined"
                                style="color: #ffc107 ; margin: auto 0; display: flex;">
                                arrow_forward
                            </li>

                            <li class="nav-item active align-content-lg-end" style="display: flex; max-width: auto;">
                                <a class="nav-link" style="color: #ffc107 ;margin: auto;" href="#">Receiving information</a>
                            </li>
                            <li class="nav-item active align-content-lg-end material-symbols-outlined"
                                style="color: #ffc107 ; margin: auto; display: flex;">
                                arrow_forward
                            </li>
                            <li class="nav-item" style="display: flex;">
                                <a class="nav-link" style="color: #ffc107 ;margin: auto;" href="#">Bird nest service</a>
                            </li>
                            <li class="nav-item active align-content-lg-end material-symbols-outlined"
                                style="color: #ffc107 ; margin: auto;">
                                arrow_forward
                            </li>
                            <li class="nav-item" style="display: flex;">
                                <a class="nav-link" style="color: #ffc107 ;margin: auto; " href="#">Pay</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </nav>
        </div>
        <!-- header -->
        <form action="Checkout" class="container py-3 h-100 " method="post">
            <jsp:useBean id="utilPrice" class="Utils.FormatCurrency"></jsp:useBean>

                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12">
                        <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                            <div class="card-body p-0">
                                <div class="row g-0">
                                    <div class="col-lg-8">
                                        <div class="p-5">
                                        <jsp:include page="/components/infoPayment.jsp"></jsp:include>

                                            <hr class="my-4">

                                            <div class="d-flex justify-content-between align-items-center mb-3">
                                                <h4 class="fw-bold mb-0 text-black">Private and policy</h4>
                                            </div>
                                            <div class="">
                                                <h7>- Advance payment for each egg multiplied by the number of customers buying.
                                                </h7>
                                                <br>
                                                <h7>- Egg incubation fees:</h7>

                                                <ul>
                                                    <li>
                                                        For 0 - 3 eggs: The price for incubating each egg is 2 million
                                                        Vietnamese
                                                        dong per month.
                                                    </li>
                                                    <li>For 4 - 7 eggs: The price for incubating each egg is 3 million
                                                        Vietnamese
                                                        dong per month.</li>
                                                    <li>For 8 eggs and above: The price is 4 million Vietnamese dong per month.
                                                    </li>
                                                </ul>
                                                <h7>- The total amount will be equal to the sum of the egg cost and the
                                                    incubation fee.</h7>
                                                <label class="container"
                                                       style="text-align:right; font-weight: bold; font-style: italic;">
                                                    <input type="checkbox" required="">
                                                    <span class="checkmark"></span>
                                                    Agree to private and policy
                                                </label>

                                            </div>

                                            <hr class="my-4">



                                            <div class="pt-5">
                                                <h6 class="mb-0"><a href="#!" class="text-body"><i
                                                            class="fas fa-long-arrow-alt-left me-2"></i>Back</a>
                                                </h6>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 bg-grey">
                                        <div class="p-5">
                                            <div class="d-flex justify-content-between align-items-center mb-3">
                                                <h4 class="fw-bold mb-0 text-black">Order</h4>
                                                <a href="product" class="change-icon change-infor-link text-decoration-none">
                                                    <h6 class="mb-0 text-muted update_info">Update</h6>
                                                </a>
                                            </div>
                                            <hr class="my-4">
                                        <c:set var="bird_nest" value="${sessionScope.BIRD_NEST_CHOOSE}"></c:set>

                                            <div class="row mb-12 my-3 d-flex justify-content-between align-items-center">
                                                <div class="col-md-4">
                                                    <img src="${bird_nest.image}"
                                                     class="img-fluid rounded-3" alt="Bird Nest Image">
                                            </div>
                                            <div class="col-md-8 justify-content-center">
                                                <h5 class="text-black mb-0">${bird_nest.name}</h5>
                                                <div class="d-flex justify-content-between align-items-center mb-0">
                                                    <h6 class="mb-0 text-muted ">Qty: ${bird_nest.quantityBuy}</h6>
                                                    <a href="#" class="change-infor-link text-decoration-none">
                                                        <h6 class="mb-0 text-muted ">x ${utilPrice.FormatPrice(bird_nest.price)}</h6>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="Promotion-received-element d-flex justify-content-between ">
                                            <h6>Service: Egg incubation is available</h6>
                                        </div>

                                        <div class="d-flex justify-content-between align-items-center mb-0">
                                            <h6 class="mb-0 text-muted ">Price of the service</h6>
                                            <a href="#" class="change-infor-link text-decoration-none">
                                                <h6 class="mb-0 text-muted ">x ${utilPrice.FormatPrice(sessionScope.SERVICE_PRICE)}</h6>
                                            </a>
                                        </div>

                                        <hr class="my-4">

                                        <h5 class="text-uppercase mb-2">Voucher</h5>
                                        <div class="input-group rounded mb-3">
                                            <input type="search" class="form-control rounded"
                                                   placeholder="Enter your voucher" aria-label="Search"
                                                   aria-describedby="search-addon" />
                                            <span class="input-group-text border-0" id="search-addon">
                                                <a href="#"><i class="fas fa-search"></i></a>
                                            </span>
                                        </div>


                                        <hr class="my-4">
                                        <c:set var="total_order" value="${bird_nest.price * bird_nest.quantityBuy + sessionScope.SERVICE_PRICE}"></c:set>
                                            <div class="d-flex justify-content-between mb-1">
                                                <h6 class="text-uppercase">Temporary</h6>
                                                <h6>${utilPrice.FormatPrice(total_order)}</h6>
                                            <input type="hidden" name="txtTotalOrder" value="${total_order}" />
                                        </div>
                                        <div class="d-flex justify-content-between mb-1">
                                            <h6 class="text-uppercase">Transport Fee</h6>
                                            <h6 id="Ship">${utilPrice.FormatPrice(sessionScope.SHIPPING_CASH)}</h6>
<!--                                            <input type="hidden" name="txtShippingCash" value="${sessionScope.SHIPPING_CASH}"/>-->
                                        </div>
                                        <div class=" d-flex justify-content-between mb-3">
                                            <h6 class="text-uppercase">discount</h6>
                                            <h6>0</h6>
                                        </div>
                                        <div class="d-flex justify-content-between mb-2">
                                            <h5 class="text-uppercase">Total price</h5>

                                            <h5 id="total_order">${utilPrice.FormatPrice(total_order + total_order * 0 + sessionScope.SHIPPING_CASH)}</h5>
                                            <input name="total_order_final" type="hidden" value="${total_order + total_order * 0 + sessionScope.SHIPPING_CASH}">
                                        </div>
                                        <input type="hidden" name="txtServiceID" value="${sessionScope.SERVICE_ID}" />
                                        <input name="btAction" value="Order" type="submit"
                                               class="btn btn-dark btn-block btn-lg" data-mdb-ripple-color="dark"
                                               style="background-color:rgb(13,103,128) ;" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>

        <!-- footer -->
        <jsp:include page="/components/footer.jsp"></jsp:include>
        <!-- close footer -->
        <script>
            function submit() {
                document.querySelector(".myForm").onsubmit();
            }
        </script>
    </body>
</html>

