// TODO gør så img-tag onclick
//      - sæt det forrest!!!!!

// TODO teksbokst
//      - gør så man kan komme ind i p-tag hvis alt teksten er slettet
//      - slet boks ved delete-knap + kryds i hjørne

const slide = document.getElementById("slide");

/**
 * Lytter efter tryk på 'backspace' eller 'delete' på hele doc og sletter elementet med fokus på slide
 * */
document.addEventListener('keydown', function (e) {
    // gemmer det element som har fokus i variabel
    const focusedEl = document.activeElement
    // hvis det har dragAndResizeContainer-klassen VED vi at det enten er: textBox, image (eller video) og så vil vi gerne slette elementet
    if(focusedEl.classList.contains("dragAndResizeContainer")){
        // enten
        if (e.key === 'Delete' || e.key === 'Backspace') {
            // og så vil vi gerne
            deleteElement(focusedEl);
        }
    }
});

let textBoxesOnSlide = [];
let imageContainers = []; // indeholder alle divImageContainers som indeholder img-elementer

let newImageId = 0;
let newTextBoxId = 0;



// henter alle knapper på siden
// TODO hent også buttons fra dropdowns/giv dem id

/*
const inpImage = document.getElementById("btnImage");
const inpVideo = document.getElementById("inpVideo");

const btnBold = document.getElementById("btnBold");
const btnItalic = document.getElementById("btnItalic");
const btnUnderline = document.getElementById("btnUnderline");
const btnTextColour = document.getElementById("btnTextColour");

const btnFont = document.getElementById("btnFont");
const btnFontSize = document.getElementById("btnFontSize");
const btnMarginLeft = document.getElementById("btnMarginLeft");
const btnMarginCentre = document.getElementById("btnMarginCentre");
const btnMarginRight = document.getElementById("btnMarginRight");
const btnList = document.getElementById("btnList");

const btnFullscreen = document.getElementById("btnFullscreen");

 */
const inpTitle = document.getElementById("inpTitle");

const btnTextBox = document.getElementById("btnTextBox");
btnTextBox.addEventListener('click', addTextToSlide);

const btnSave = document.getElementById("btnSave");
btnSave.addEventListener('click', saveSlide);


/**
 * Omdanner data på slide og sender i fetch
 * */
function saveSlide(){
    const url = `http://localhost:8081/saveSlide`;

    let title = inpTitle.value;

    // hvis de har indtastet en titel
    if(title.length > 0){

        // TODO tilføj noget async
        const images = convertImagesToJSON();

        console.log(images);

        let slide = {
            "title": title,
            "images": images
        }

        const body = JSON.stringify(slide);

        console.log(body);

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // betyder == vi sender et json i string-format
            },
            body: body
        };

        // TODO tilføj måske noget async
        function checkIfSuccess(response){
            if(response.status >= 200 && response.status < 300){
                // TODO har vi overhovedet brug for denne cookie??
                const cookie = document.cookie;

                alert(cookie);

                window.location.replace("/");
            }
            else if(response.status === 409){
                alert("Der findes allerede et slide med denne title, vælg venligst en anden.");
            }
            // else bliver den catchet i fetch
        }
        fetch(url, requestOptions)
            .then(data => checkIfSuccess(data))
            .catch(error => console.log("Fejl i fetch, createSlide.js: ", error));


    } else{
        alert("Indtast venligst en titel");
    }
}
// TODO tilføj noget async noget så vi ved vi har fået hentet dataen
// kig på: https://stackoverflow.com/questions/29775797/fetch-post-json-data
/**
 * Omdanner alle img-tags på liste til JSON
 * */
function convertImagesToJSON(){
    // returnerer en liste hvor hvert el på imageContainters-listen har undergået makeImageJSON-func
   return imageContainers.map(div => makeImageJSON(div));

    function makeImageJSON(div){

        const img = div.getElementsByTagName('img')[0];

        let top =  div.style.top;
        let left = div.style.left;

        // hvis de er 0 kommer de ud som "" - derfor sætter vi lige værdien til 0px
        // også < 0, hvis der nu er sket en fejl på en eller anden måde på slidet
        if(top === "" || top < 0) top = "0px";
        if(left === "" || left < 0) left = "0px";

        // TODO tilføj z-index (måske også en bool: isFullscreen)
        return {
            "top": top,
            "left": left,
            "width": img.offsetWidth + "px",
            "height": img.offsetHeight + "px",
            "z-index": img.style.zIndex,
            "base64": img.src,
        }
    }
}








// TODO Få linebreak til at virker og fjern template tekst i box når der trykkes.
// tilføj text box
function addTextToSlide(){
    newTextBoxId++;

    // div-container
    const divTextBoxContainer = document.createElement('div');
    divTextBoxContainer.setAttribute('id',"pDivContainer" + newTextBoxId);
    divTextBoxContainer.classList.add("dragAndResizeContainer");
    divTextBoxContainer.classList.add("textBoxDivSize");

    // p-tag
    const textBox = document.createElement('p');
    textBox.setAttribute('id', "textBoxId" + newTextBoxId);
    textBox.innerText = "Tryk her for at tilføje tekst";
    textBoxesOnSlide.push(textBox);


    //http://jsfiddle.net/GeJkU/
    function divClicked() {
        let divHtml = $(this).html();
        let editableText = $("<textarea />");
        editableText.val(divHtml);
        $(this).replaceWith(editableText);
        editableText.focus();
        // setup the blur event for this new textarea
        editableText.blur(editableTextBlurred);
    }

    function editableTextBlurred() {
        let html = $(this).val();
        let viewableText = $("<div>");
        viewableText.html(html);
        $(this).replaceWith(viewableText);
        // setup the click event for this new div
        viewableText.click(divClicked);
    }

    $(document).ready(function() {
        $("p").click(divClicked);
    });

    $(function() {
        $('.dragAndResizeContainer')
            .draggable({
                containment: "#slide",
                cursor : "move"})
            .resizable({
                containment: "#slide",
                handles: "se", // hive i alle hjørner
                maxHeight: 630,
                maxWidth: 1120,
                autoHide: true // gemmer hive-firkanter når man ikke har musen over elementet
            });
    });

    // tilføjer til DOM
    divTextBoxContainer.appendChild(textBox);
    slide.appendChild(divTextBoxContainer);
}

// tilføj billede
let addImageToSlide = function(event) {

    let reader = new FileReader();

    let file = event.target.files[0];

    reader.readAsDataURL(file);
    reader.onloadend = function(){
        newImageId++;

        // vi henter base64
        let base64 = reader.result;

        // ---------DIV-container til nyt image
        const divImageContainer = document.createElement('div');
        divImageContainer.setAttribute('id',"imageDivContainer" + newImageId);
        divImageContainer.classList.add("dragAndResizeContainer");
        divImageContainer.tabIndex = 0;  // sætter focus på billede når man trykker
        //! såden er kalder vi en funktion som tager parametre i en EventListener!!!!!!!!
        divImageContainer.addEventListener('dblclick', function (){makeFullScreen(divImageContainer)});
        divImageContainer.addEventListener('mouseup',function(){printPosition(divImageContainer)}); // TODO slet
        divImageContainer.addEventListener('click', function (){addFocusAndZIndex(divImageContainer)});


        /*tilføj til liste
        * det er divContainer som tilføjes fordi det er den som ved hvorhenne på slidet den er
        * fordi img-taggets top og left er ift. divContainer
        * */
        imageContainers.push(divImageContainer);

        // --------IMG
        const imgNewImage = document.createElement('img');
        imgNewImage.setAttribute('id',"image" + newImageId);
        imgNewImage.src = base64;


        // TODO span delete-kryds
        const spanDelete = document.createElement('span');
        spanDelete.classList.add("floating", "top", "right", "ui-icon", "ui-icon-close");
        spanDelete.addEventListener('click',function(){deleteElement(divImageContainer)});



        // tilføjer til DOM
        imgNewImage.appendChild(spanDelete);
        divImageContainer.appendChild(imgNewImage);
        slide.appendChild(divImageContainer);
        // TODO når man sletter et element skal det også slettes fra imagesOnSlide-array'et
        //     - her skal vi også slette det tomme felt der kommer på array'et når man slette elementet fra listen


        // gør billedet draggable og resizable indenfor slide-div'en
        $(function() {
            $('.dragAndResizeContainer')
                .draggable({
                    containment: "#slide",
                    cursor : "move"})
                .resizable({
                    containment: "#slide",
                    handles: "se", // hive i alle hjørner
                    aspectRatio: true, // hive med samme format
                    maxHeight: 630,
                    maxWidth: 1120,
                    autoHide: true // gemmer hive-firkanter når man ikke har musen over elementet
            });
        });
    }
};

function printPosition(el){
    console.log("top: " + el.style.top +
        " left: " + el.style.left +
        " width: " + el.offsetWidth +
        " height: " + el.offsetHeight);
}

/**
 * Hvis billedet allerede er fulscreen bliver det 80%
 * Hvis billedet IKKE er halv størrelse, bliver det fullscreen
 *
 * */
function makeFullScreen(el){

    let width = el.offsetWidth;
    let height = el.offsetHeight;

    // hvis den allerede er i fuldskærm
    if(width === 1120 || height === 630){
        // gør 80%
        el.style.width = width * 0.8 + "px";
        el.style.height = height * 0.8 + "px";
    }

    // else gør til fuldskærm
    else if(width > height){
        // TODO tjek noget aspect-ratio
        // desiredWidth / actualWidth == størrelsesforholdet
        const ratio = 1120/width;
        el.style.width = "1120px";
        el.style.height = height * ratio + "px";
        el.style.left = "0px";
    }
    else{ // gør til fuldskærm på height
        const ratio = 630/height;
        el.style.height = "630px";
        el.style.width = width * ratio + "px";
        el.style.top = "0px";
    }
}

function addFocusAndZIndex(el){
    // TODO find ud af dette så billderne ikke står ovenihinanden

    imageContainers.forEach(con => con.style.zIndex = 0);

    el.style.zIndex = 1;
    el.focus();
}



function deleteElement(el){
    jQuery(el).fadeOut(function(){el.remove();});

}
























//! RESTEN ER NOGET GAMMELT LORT
//imageForm.addEventListener('submit', handleImageSubmit);
// når der trykkes på submit fanger vi dataen fra imageInputfelt og gemmer det på liste
async function handleImageSubmit(event){
    alert("HEJ4");


    /**
     * This prevents the default behaviour of the browser submitting
     * the form so that we can handle things instead.
     */
    // event.preventDefault();

    /**
     * This gets the element which the event handler was attached to.
     *
     * @see https://developer.mozilla.org/en-US/docs/Web/API/Event/currentTarget
     */
    //const form = event.currentTarget;

    /**
     * This takes the API URL from the form's `action` attribute.

     const url = form.action;
     */

    try {
        /**
         * This takes all the fields in the form and makes their values
         * available through a `FormData` instance.
         *
         * @see https://developer.mozilla.org/en-US/docs/Web/API/FormData
         */
        const formData = new FormData(imageForm);

        console.log("formData", formData);

        /**
         * We'll define the `postFormDataAsJson()` function in the next step.
         */
        const imageAsJSON = await convertImageToJSON(formData);

        console.log("imageAsJSON: ", imageAsJSON);

    } catch (error) {
        console.error(error);
    }
}
async function convertImageToJSON(formData) {
    alert("HEJ4");

    /**
     * We can't pass the `FormData` instance directly to `fetch`
     * as that will cause it to automatically format the request
     * body as "multipart" and set the `Content-Type` request header
     * to `multipart/form-data`. We want to send the request body
     * as JSON, so we're converting it to a plain object and then
     * into a JSON string.
     *
     * @see https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST
     * @see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/fromEntries
     * @see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON/stringify
     */
    const plainFormData = Object.fromEntries(formData.entries());
    const formDataJsonString = JSON.stringify(plainFormData);

    return formDataJsonString;
}
// kaldes når man trykker på imageInputfelt - sørger for at submitte billedet
function imageAutoSubmit(input){
    alert("HEJ");

    // hvis der er noget i input-feltet for image
    if (input.files && input.files[0]) {
        alert("HEJ2");
        let reader = new FileReader();
        reader.onload = function (e) {
            alert("HEJ2.5");

            handleImageSubmit().then(answer => alert(answer));


            // document.forms["imageForm"].submit();
            // document.imageForm.submit();
            alert("HEJ3");


            // TODO lav nyt imagetag

            $('#blah')
                .attr('src', e.target.result)
                .width(150)
                .height(200);



        };

        reader.readAsDataURL(input.files[0]);

    }
}
// til upload af billede
function readURL(input) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}









/*
const canvasContext = canvas.getContext("2d");
canvasContext.font = "30px Arial";
canvasContext.strokeText("Hello World",10,50);


const canvasContext1 = canvas.getContext("2d");
canvasContext1.font = "60px Arial";
canvasContext1.strokeText("Heeeeej",30,90);
*/










