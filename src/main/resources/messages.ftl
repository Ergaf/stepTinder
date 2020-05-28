<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

    <style type="text/css">
        <#include "css/bootstrap.min.css">
        <#include "css/style.css">
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="navigate-cont">
                    <a class="btn btn-lg btn-light" href="/user">Users</a>
                    <a class="btn btn-lg btn-light" style="float: right" href="/liked">Liked</a>
                </div>
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h6 class="ml-1 mb-0">Ketty Peris</h6>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <i class="fa fa-times hover text-center pt-1"></i>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                        <i class="fa fa-video-camera mr-3"></i>
                        <i class="fa fa-user-plus"></i>
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <ul class="p-0">
                        <#list message as message>
                            <#if message.msgForThisUser == true>
                            <li class="send-msg float-right mb-2">
                                <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                    ${message.text}
                                </p>
                                <span class="receive-msg-time">${message.time}</span>
                            </li>
                            <#else>
                                <li class="receive-msg float-left mb-2">
                                    <div class="receive-msg-desc float-left ml-2">
                                        <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                            ${message.text}
                                        </p>
                                        <span class="receive-msg-time">${message.time}</span>
                                    </div>
                                </li>
                            </#if>
                        </#list>
                    </ul>
                </div>
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <div class="row">
                        <div class="col-md-2 options-left">
                            <i class="fa fa-smile-o"></i>
                        </div>
                        <div class="col-md-7 pl-0">
                            <input id="messageinput" type="text" class="border-0" placeholder=" Send message" />
                        </div>
                        <div class="col-md-3 text-right options-right">
                            <i class="fa fa-picture-o mr-2"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    document.addEventListener("keydown", async function () {
        console.log(event.keyCode);
        let value = document.querySelector("#messageinput").value
        if(event.keyCode === 13 && value && value !== " "){
            console.log("otpravliayu");
            let data = {
                text: value
            }
            let res = await createFetch("/messages", "POST", data)
            console.log(res);
            if(res){
                console.log("сообщение дошло!");
            } else {
                console.log("фигня какая то)");
            }
        }


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
    })
</script>
</body>
</html>