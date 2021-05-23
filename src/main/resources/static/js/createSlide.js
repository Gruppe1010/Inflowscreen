// TODO gør så img-tag onclick
//      - sæt det forrest!!!!!

// TODO teksbokst
//      - gør så man kan komme ind i p-tag hvis alt teksten er slettet
//      - slet boks ved delete-knap + kryds i hjørne

// ! HUSK når vi tager coordinater ud for placering så skal vi tjekke om de er minus og hvis de er minus == 0


const slide = document.getElementById("slide");

let newImageId = 0;
let newTextBoxId = 0;





// henter alle knapper på siden
// TODO hent også buttons fra dropdowns/giv dem id
const inpTitle = document.getElementById("inpTitle");

const btnTextBox = document.getElementById("btnTextBox");
/*
const inpImage = document.getElementById("inpImage");
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

const btnSave = document.getElementById("btnSave");

const imageForm = document.getElementById('imageForm');


// når der submittes
btnSave.addEventListener('click', saveSlide);
btnTextBox.addEventListener('click', addTextToSlide);


function saveSlide(){
    let title;
    const url = `http://localhost:8081/saveSlide`;

    // TODO tilføj noget async noget så vi ved vi har fået hentet dataen
    function retrieveInput(){
        title = inpTitle.value;
        console.log("Title er her: " + title);

    }
    retrieveInput();



    // hvis de har indtastet en titel
    if(title.length > 0){
        // TODO tilføj måske noget asyng
        function createJSONSlide(title){
            // vi laver et object (JSON-obj er standard obj i js)
            let slide = {
                "title": title
            };

            /* Vi laver JSON om til en String
               Fordi at body i requestOptions'en skal angives som string
             */
            return JSON.stringify(slide);
        }
        const body = createJSONSlide(title);

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // betyder == vi sender et json i string-format
            },
            body: body
        };

        // TODO tilføjet måske noget async
        function checkIfSuccess(response){
            if(response.status >= 200 && response.status < 300){

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
    }
    else{
        alert("Indtast venligst en title.");
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
        divImageContainer.addEventListener('dblclick', function (){makeFullScreen(divImageContainer)});

        //! såden er kalder vi en funktion som tager parametre i en EventListener!!!!!!!!
        divImageContainer.addEventListener('mouseup',function(){printPosition(divImageContainer)});


        // --------IMG
        const imgNewImage = document.createElement('img');
        imgNewImage.src = base64;
        imgNewImage.setAttribute('id',"image" + newImageId);

        const spanDelete = document.createElement('span');
        spanDelete.classList.add("floating", "top", "right", "ui-icon", "ui-icon-close");
        spanDelete.title = "delete";
        spanDelete.addEventListener('click',function(){deleteElement(divImageContainer)});


        // sætter focus på billede når man trykker
        imgNewImage.tabIndex = 0;
        imgNewImage.addEventListener('click', function (){addFocus(imgNewImage)});


        // tilføjer til DOM
        imgNewImage.appendChild(spanDelete);
        divImageContainer.appendChild(imgNewImage);
        slide.appendChild(divImageContainer);


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


function addFocus(el){
    el.focus();
}


function deleteElement(el){
    el.fadeOut(function() {
        el.remove();
    });
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










