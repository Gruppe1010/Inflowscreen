const slideBody = document.getElementById('slide');


//https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch
async function getAllSlides() {
    // Default options are marked with *
    return await fetch(`http://localhost/api/slideshow/${orgId}`).then(response => response.json());
}

let current = 0;

getAllSlides()
    .then(slideJSONs => createSlideDivs(slideJSONs))
    .then(slideDivs =>
        setInterval(function() {

            for (let i = 0; i < slideDivs.length; i++) {
                slideDivs[current].style.opacity = 0;
                slideDivs[current].style.transition = "opacity 3s ease-in-out";

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

            current = (current != slideDivs.length - 1) ? current + 1 : 0;
            slideDivs[current].style.opacity = 1;
        }, 6000));



function createSlideDivs(slideJSONs){
    return slideJSONs.map(slideJSON => createSlide(slideJSON));
}

function createSlide(slideJSON){
    /* TIL OVERS/MAGLER AT BLIVE HÅNDTERET:
        private int frequency;
        private Set<SlideVideoDTO> slideVideoDTOs;
        private String themePath;
    * */


    const ratio = slideBody.offsetHeight / 630;

    const slideDiv = document.createElement('div');

    const slideImages = slideJSON.slideImageDTOs;
    slideImages.forEach(addImageToSlide);

    // TODO tilføj async
    function addImageToSlide(image){
        const img = document.createElement('img');
        img.src = image.base64;

        img.style.zIndex = image.zIndex;
        img.style.top = Number(image.top) * ratio + "px";
        img.style.left = Number(image.left) * ratio + "px";
        img.style.width = Number(image.width) * ratio + "px";
        img.style.height = Number(image.height) * ratio + "px";

        slideDiv.appendChild(img);
    }

    const textBoxes = slideJSON.textBoxDTOs;
    textBoxes.forEach(addTextBoxToSlide);

    /*
    private boolean isList;
   */
    function addTextBoxToSlide(textBox){

        const span = document.createElement('span');

        span.style.zIndex = 90000;
        span.value = textBox.text;
        span.style.top = Number(textBox.top) * ratio + "px";
        span.style.left = Number(textBox.left) * ratio + "px";
        span.style.width = Number(textBox.width) * ratio + "px";
        span.style.height = Number(textBox.height) * ratio + "px";

        span.style.fontFamily = textBox.font;
        span.style.fontSize = textBox.fontSize * ratio;
        span.style.color = textBox.fontColour;
        span.style.textAlign = textBox.margin;

        if(textBox.isBold) span.style.fontWeight = 'bold';
        if(textBox.isItalic) span.style.fontStyle = 'italic';
        if(textBox.isUnderlined) span.style.textDecoration = 'underline';


        slideDiv.appendChild(span);
    }



    return slideDiv;
}




// GAMLE FUNKTION
/*
let current = 0,
    slides = document.getElementsByClassName("fade-slide");


setInterval(function() {

    for (let i = 0; i < slides.length; i++) {
        slides[current].style.opacity = 0;
        slides[current].style.transition = "opacity 3s ease-in-out";

    }



    current = (current != slides.length - 1) ? current + 1 : 0;
    slides[current].style.opacity = 1;
}, 6000);

 */




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