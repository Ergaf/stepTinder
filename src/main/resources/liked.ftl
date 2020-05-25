<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
<#--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">-->
    <style>
        <#include "css/bootstrap.min.css">
<#--        <#include "css/style.css">-->
        .img-circle{
            height: 50px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="navigate-cont">
                    <a class="btn btn-lg btn-light" href="/user">Users</a>
                    <a class="btn btn-lg btn-light" style="float: right" href="/liked">Liked</a>
                </div>
                <div class="panel-heading">
                    <h3 class="panel-title">Liked List</h3>
                </div>
                <#list profile as value>
                <a href="/messages" class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img class="img-circle" src=${value.photo}  />  


                                        </div>

                                    </td>
                                    <td class="align-middle">
                                        ${value.name}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </a>
                </#list>
            </div>
        </div>
    </div>
</div>

</body>
</html>