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
    <div class="navigate-cont">
        <a class="btn btn-lg btn-light" href="/user">UsersForLike</a>
        <a class="btn btn-lg btn-light" style="float: right" href="/liked">Liked</a>
    </div>
    <div>вы просмотрели всех!</div>
    </div>
</div>

<script>
    let id = document.querySelector("h3").getAttribute("id")
    document.querySelector(".btn-outline-danger").addEventListener("click", async function () {
        let data = {
            id: id,
            str: false
        }
        let res = await createFetch("/user", "POST", data)
        console.log(res);
        location=res;
    })
    document.querySelector(".btn-outline-success").addEventListener("click", async function () {
        let data = {
            id: id,
            str: true
        }
        let res = await createFetch("/user", "POST", data)
        console.log(res);
        location=res;
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
        let res = await response.text();
        return res;
    }
</script>
</body>
</html>