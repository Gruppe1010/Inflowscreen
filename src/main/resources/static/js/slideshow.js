const slideBody = document.getElementById('slide');


//https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch
async function getAllSlides() {
    //const url = `http://localhost/api/slideshow/${orgId}`; // localhost
    const url = `http://inflowscreen.dk/api/slideshow/${orgId}`; // online


    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json', // betyder == vi sender et json i string-format
        },
        redirect: 'follow'
    };

    // Default options are marked with *
    return await fetch(url, requestOptions)
        .then(response => response.json());//response.json());


}

let current = 0;

getAllSlides()
    .then(slideJSONs => createSlideDivs(slideJSONs))
    .then(slides => console.log(slides))
    .then(slideDivs =>
        setInterval( function() {

            for (let i = 0; i < slideDivs.length; i++) {
                slideDivs[current].style.opacity = 0;
                slideDivs[current].style.transition = "opacity 3s ease-in-out";

            }


            current = (current != slideDivs.length - 1) ? current + 1 : 0;
            slideDivs[current].style.opacity = 1;
        }, 6000)
    );






async function createSlideDivs(slideJSONs){
    return await slideJSONs.map(slideJSON => createSlide(slideJSON));
}

function createSlide(slideJSON){
    /* TIL OVERS/MAGLER AT BLIVE HÅNDTERET:
        private int frequency;
        private Set<SlideVideoDTO> slideVideoDTOs;
        private String themePath;
    * */

    const ratio = slideBody.offsetWidth / 1120;

    const slideDiv = document.createElement('div');
    slideDiv.classList.add('fade-slide');
    slideDiv.style.height = '100vh';
    slideDiv.style.width = '100vw';


    const slideImages = slideJSON.slideImageDTOs;
    slideImages.forEach(addImageToSlide);

    // TODO tilføj async
    async function addImageToSlide(image){
        const img = document.createElement('img');
        img.classList.add('slide-el');

        img.src = image.base64;

        img.style.zIndex = image.zIndex;
        img.style.top = Number(image.top) * ratio + "px";
        img.style.left = Number(image.left) * ratio + "px";
        img.style.width = Number(image.width) * ratio + "px";
        img.style.height = Number(image.height) * ratio + "px";

        console.log(slideDiv);

        await slideDiv.appendChild(img);
    }

    const textBoxes = slideJSON.textBoxDTOs;
    textBoxes.forEach(addTextBoxToSlide);

    /*
    private boolean isList;
   */
    async function addTextBoxToSlide(textBox){

        const span = document.createElement('span');
        span.classList.add('slide-el');

        span.style.zIndex = 90000;
        span.innerText = textBox.text;
        span.style.top = Number(textBox.top) * ratio + "px";
        span.style.left = Number(textBox.left) * ratio + "px";
        span.style.width = Number(textBox.width) * ratio + "px";
        span.style.height = Number(textBox.height) * ratio + "px";

        span.style.fontFamily = textBox.font;
        span.style.fontSize = Number(textBox.fontSize) * ratio + "px";
        span.style.color = textBox.fontColour;
        span.style.textAlign = textBox.margin;

        if(textBox.isBold) span.style.fontWeight = 'bold';
        if(textBox.isItalic) span.style.fontStyle = 'italic';
        if(textBox.isUnderlined) span.style.textDecoration = 'underline';


        await slideDiv.appendChild(span);
    }


    slideBody.appendChild(slideDiv);
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