
let current = 0,
    slides = document.getElementsByClassName("fadeimage");

setInterval(function() {


    //slides.forEach(slide => slide.fadeOut());


    // jQuery(el).fadeOut(function(){el.remove();});

    for (let i = 0; i < slides.length; i++) {
        jQuery(slides[i]).fadeOut();
    }

    /*
    current = (current != slides.length - 1) ? current + 1 : 0;
    slides[current].style.opacity = 1;

     */
    /*
    Elvis operator:
        x = f() ?: g()
            ELLER
        x = f() ? f() : g()
        hvis f()==true --> x == f()
        hvis f()==false --> x == g()
    * */

    /*
    * (current != slides.length - 1) == hvis current IKKE er den sidste på slide-array
    * */
    current = (current != slides.length - 1) ? current + 1 : 0;
    jQuery(slides[current]).fadeIn();
}, 3000);


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