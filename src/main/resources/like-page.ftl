<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <style type="text/css">
        <#include "css/bootstrap.min.css">
        <#include "css/style.css">
    </style>
</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
                    <img src="https://robohash.org/68.186.255.198.png" alt="" class="mx-auto rounded-circle img-fluid">
                    <h3 class="mb-0 text-truncated">User name</h3>
                    <br>
                </div>
                <div class="col-12 col-lg-6">
                    <button type="button" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                </div>
                <div class="col-12 col-lg-6">
                    <button class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                </div>
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

<script>
    document.querySelector(".btn-outline-danger").addEventListener("click", async function () {
        let data = {
            str: false
        }
        let res = await createFetch("/user", "POST", data)
        console.log(res);
    })
    document.querySelector(".btn-outline-success").addEventListener("click", async function () {
        let data = {
            str: true
        }
        let res = await createFetch("/user", "POST", data)
        console.log(res);
    })

    async function createFetch(adres, method, data){
        const response = await fetch(adres, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        }).catch(e => {console.log('Bad URL: ', e)});
        let res = await response.json();
        return res;
    }
</script>
</body>
</html>