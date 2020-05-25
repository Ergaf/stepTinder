<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Registration</title>

    <style type="text/css">
        <#include "css/bootstrap.min.css">
        <#include "css/style.css">
    </style>
</head>

<body class="text-center">
<form class="form-signin">
    <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Registration</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="text" id="inputEmail" class="form-control" placeholder="Login" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    <a href="/login" class="btn btn-lg btn-dark btn-block">back</a>
    <p class="mt-5 mb-3 text-muted">&copy; ПсевдоТиндер 2020</p>
</form>

<script>

    document.querySelector(".btn-primary").addEventListener("click", async function () {
        event.preventDefault()
        let name = document.querySelector("#inputEmail").value.toLowerCase();
        let pass = document.querySelector("#inputPassword").value
        if(name && pass){
            let data = {
                name: name,
                pass: pass
            }
            console.log(JSON.stringify(data));
            let res = await createFetch("/registration", "POST", data)
            console.log(res);
            if(!res){
                alert("user exist")
            } else {
                location="/user"
            }
        }
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