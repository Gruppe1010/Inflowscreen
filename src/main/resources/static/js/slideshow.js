
let current = 0,
    slides = document.getElementsByClassName("fadeimage");

setInterval(function() {
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.opacity = 0;
    }
    current = (current != slides.length - 1) ? current + 1 : 0;
    slides[current].style.opacity = 1;
}, 3000);


//TODO link til ur3 http://jsfiddle.net/cse_tushar/fKKSb/311/
(function () {
    function checkTime(i) {
        return (i < 10) ? "0" + i : i;
    }

    function startTime() {
        let today = new Date(),
            h = checkTime(today.getHours()),
            m = checkTime(today.getMinutes()),
            d = checkTime(today.getDate()),
            mo = checkTime(today.getMonth()),
            y = checkTime(today.getFullYear())
        document.getElementById('time').innerHTML = h + ":" + m;
        document.getElementById('date').innerHTML = d + "/" + mo + "/" + y;
        t = setTimeout(function () {
            startTime()
        }, 500);
    }
    startTime();
})();


$(document).ready(function () {

    function time() {
        let meridiem = "AM";
        let currentTime = new Date();
        let hour = currentTime.getHours();
        if (hour < 10)
            hour = "0" + hour;
        if (hour > 12) {
            hour = hour - 12;
        }
        if (hour === 0)
            hour = 12;

        let minute = currentTime.getMinutes();
        if (minute < 10)
            minute = "0" + minute;
        let day = currentTime.getDay();

        if (day === 0)
            day = "Søndag";
        else if (day === 1)
            day = "Mandag";
        else if (day === 2)
            day = "Tirsdag";
        else if (day === 3)
            day = "Onsdag";
        else if (day === 4)
            day = "Torsdag";
        else if (day === 5)
            day = "Fredag";
        else if (day === 6)
            day = "Lørdag";

        let month = currentTime.getMonth();

        if (month === 0)
            month = "JAN";
        else if (month === 1)
            month = "FEB";
        else if (month === 2)
            month = "MAR";
        else if (month === 3)
            month = "APR";
        else if (month === 4)
            month = "MAJ";
        else if (month === 5)
            month = "JUN";
        else if (month === 6)
            month = "JUL";
        else if (month === 7)
            month = "AUG";
        else if (month === 8)
            month = "SEP";
        else if (month === 9)
            month = "OKT";
        else if (month === 10)
            month = "NOV";
        else if (month === 11)
            month = "DEC";

        let year = currentTime.getFullYear();

        let date = currentTime.getDate();

        let yearDiv = document.getElementById('year');
        let monthDiv = document.getElementById('month');
        let clockDiv = document.getElementById('clock');
        let dayDiv = document.getElementById('day');
        let dateDiv = document.getElementById('date');

        dayDiv.innerText = day;
        clockDiv.innerText = hour + ":" + minute;
        monthDiv.innerText = month;
        yearDiv.innerText = year;
        dateDiv.innerText = date;
    }
    time();
    setInterval(time, 1000);
});

