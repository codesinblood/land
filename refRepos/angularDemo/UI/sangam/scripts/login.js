var validateInput = function () {

    var userName = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    if((userName.trim().length == 0) || (password.length == 0)){
        alert('Enter the Valid Credential');
    } else {
        alert('Successful LogIn');
        location.href = '../ofmc/index.html';
    }
};

(function () {
    var i = 0;
    setInterval(showTime, 1000);
    function showTime() {

        i = i + 1;
        document.getElementById('timer').innerHTML = i;
    }
}) ();
