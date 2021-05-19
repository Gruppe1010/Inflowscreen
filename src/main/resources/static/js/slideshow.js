
var current = 0,
    slides = document.getElementsByTagName("img");

setInterval(function() {
    for (var i = 0; i < slides.length; i++) {
        slides[i].style.opacity = 0;
    }
    current = (current != slides.length - 1) ? current + 1 : 0;
    slides[current].style.opacity = 1;
}, 3000);



$(document).ready(function () {

    function time() {

        var currentTime = new Date();
        var hour = currentTime.getHours();
        if (hour < 10)
            hour = "0" + hour;
        if (hour > 24)
            if (hour === 0)
                hour = 12;

        var minute = currentTime.getMinutes();
        if (minute < 10)
            minute = "0" + minute;


        second = "";
        var day = currentTime.getDay();

        if (day === 0)
            day = "Sunday";
        else if (day === 1)
            day = "Monday";
        else if (day === 2)
            day = "Tuesday";
        else if (day === 3)
            day = "Wednesday";
        else if (day === 4)
            day = "Thursday";
        else if (day === 5)
            day = "Friday";
        else if (day === 6)
            day = "Saturday";

        var month = currentTime.getMonth();

        if (month === 0)
            month = "JAN";
        else if (month === 1)
            month = "FEB";
        else if (month === 2)
            month = "MAR";
        else if (month === 3)
            month = "APR";
        else if (month === 4)
            month = "MAY";
        else if (month === 5)
            month = "JUN";
        else if (month === 6)
            month = "JUL";
        else if (month === 7)
            month = "AUG";
        else if (month === 8)
            month = "SEP";
        else if (month === 9)
            month = "OCT";
        else if (month === 10)
            month = "NOV";
        else if (month === 11)
            month = "DEC";

        var year = currentTime.getFullYear();

        var date = currentTime.getDate();

        var yearDiv = document.getElementById('year');
        var monthDiv = document.getElementById('month');
        var clockDiv = document.getElementById('clock');
        var dayDiv = document.getElementById('day');
        var dateDiv = document.getElementById('date');

        dayDiv.innerText = day;
        clockDiv.innerText = hour + ":" + minute;
        monthDiv.innerText = month;
        yearDiv.innerText = year;
        dateDiv.innerText = date;
    }
    time();
    setInterval(time, 1000);

});
