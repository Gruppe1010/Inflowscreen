


//https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch
async function getAllSlides() {
    // Default options are marked with *
    return await fetch(`http://localhost/api/slideshow/${orgId}`).then(response => response.json());
}

getAllSlides()
    .then(data => {
        console.log(data); // JSON data parsed by `data.json()` call
    });











let current = 0,
    slides = document.getElementsByClassName("fade-slide");


setInterval(function() {

    for (let i = 0; i < slides.length; i++) {
        slides[current].style.opacity = 0;
        slides[current].style.transition = "opacity 3s ease-in-out";

    }

    /*
     Elvis operator:
        x = f() ?: g()
            ELLER
        x = f() ? f() : g()
        hvis f()==true --> x == f()
        hvis f()==false --> x == g()


      * (current != slides.length - 1) == hvis current IKKE er den sidste på slide-array
      * */
    current = (current != slides.length - 1) ? current + 1 : 0;
    slides[current].style.opacity = 1;
}, 6000);



//link til ur3 http://jsfiddle.net/cse_tushar/fKKSb/311/
(function () {
    function checkTime(i) {
        return (i < 10) ? "0" + i : i;
    }

    function startTime() {
        let today = new Date(),
            h = checkTime(today.getHours()),
            m = checkTime(today.getMinutes()),
            d = checkTime(today.getDate()),
            mo = checkTime(today.getMonth()+1),
            y = checkTime(today.getFullYear())
        document.getElementById('time').innerHTML = h + ":" + m;
        document.getElementById('date').innerHTML = d + "/" + mo + "/" + y;
        t = setTimeout(function () {
            startTime()
        }, 500);
    }
    startTime();
})();










// Canvas ting
/*
    const canvasContext = canvas.getContext("2d");
canvasContext.font = "30px Arial";
canvasContext.strokeText("Hello World",10,50);


const canvasContext1 = canvas.getContext("2d");
canvasContext1.font = "60px Arial";
canvasContext1.strokeText("Heeeeej",30,90);
*/