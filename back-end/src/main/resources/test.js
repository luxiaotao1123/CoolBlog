/**
 * Created by Administrator on 2017/11/28.
 */
function setMessgae() {
    stompClient.send("/index",{},JSON.stringify({'messgae':'hello'}));
}


function disConnect() {
    stompClient.discount();
    console.log("连接已断开")
}

function connect() {
    var sockjs = new SockJS('/endPoint');
    stompClient = Stomp.over(sockjs);
    stompClient.connect({},function (frame) {
        setConnected(true);
        console.log('Connected:'+frame);
        stompClient.subscribe('/topic/clients',function (response) {
                var response = $("#rongqi");
                response.html(JSON.parse(response.body).responseMessage)
            });
        });
}