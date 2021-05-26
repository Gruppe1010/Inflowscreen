// TODO teksbokst
//      - gør så man kan komme ind i p-tag hvis alt teksten er slettet
//      - slet boks ved delete-knap + kryds i hjørne

const slide = document.getElementById("slide");

Array.prototype.max = function() {
    return Math.max.apply(null, this);
};
Array.prototype.min = function() {
    return Math.min.apply(null, this);
};

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

let textBoxesOnSlide = []; // indeholder alle textBocContainers
let imageContainers = []; // indeholder alle divImageContainers som indeholder img-elementer

let newImageId = 0;
let newTextBoxId = 0;

let focusedEl;

// henter alle knapper på siden
// TODO hent også buttons fra dropdowns/giv dem id

/*
const inpImage = document.getElementById("btnImage");
const inpVideo = document.getElementById("inpVideo");


 */
// TILFØJS-knapper
const inpTitle = document.getElementById("inpTitle");
const btnTextBox = document.getElementById("btnTextBox");
btnTextBox.addEventListener('click', addTextToSlide);

// STYLINGS-knapper
const btnBold = document.getElementById("btnBold");
btnBold.addEventListener('click', setToBold);
function setToBold(){}

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
btnFullscreen.addEventListener('click', function() {makeFullScreen(focusedEl)});

// GEM-knap
const btnSave = document.getElementById("btnSave");
btnSave.addEventListener('click', saveSlide);

// ændre skifttypen i den aktive boks
let changeFontStyle = function (font) {
    focusedEl.style.fontFamily = font.value;
    // ændre skifttypen i select dropdownen
    document.activeElement.style.fontFamily = font.value;
}

/**
 * Omdanner data på slide og sender i fetch
 * */
function saveSlide(){

    const url = `http://localhost/saveSlide`; // localhost
    // const url = `http://inflowscreen.dk/saveSlide`; online

    let title = inpTitle.value;

    // hvis de har indtastet en titel
    if(title.length > 0){

        // TODO tilføj noget async
        const images = convertImagesToJSON();

        console.log(images);

        let slide = {
            "title": title,
            "slideImageDTOs": images
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
            "fileName": img.title
        }
    }
}


// TODO fjern template tekst (placeholder) i box når der trykkes.
// tilføj text box
function addTextToSlide(){
    newTextBoxId++;

    // div-container
    const divTextBox = document.createElement('div');
    divTextBox.setAttribute('id',"textBox" + newTextBoxId);
    divTextBox.setAttribute('contenteditable', "true");
    divTextBox.setAttribute('data-placeholder', 'Tryk her for at tilføje tekst');
    divTextBox.classList.add("dragAndResizeTextBoxContainer");
    divTextBox.classList.add("textBoxDivSize");
    divTextBox.addEventListener('click', function(){setAsFocusedEl(divTextBox)});

    textBoxesOnSlide.push(divTextBox);

    // draggable + resizable
    $(function() {
        $('.dragAndResizeTextBoxContainer')
            .draggable({
                containment: "#slide",
                cursor : "move"})
            .resizable({
                containment: "#slide",
                handles: "se", // hive i hjørne
                maxHeight: 630,
                maxWidth: 1120,
                autoHide: true // gemmer hive-firkanter når man ikke har musen over elementet
            });
    });


    //Fjerne placeholder text efter brugerinput
    (function ($) {
        $(document).on('change keydown keypress input', 'div[data-placeholder]', function() {
            if (this.textContent) {
                this.dataset.divPlaceholderContent = 'true';
            }
            else {
                delete(this.dataset.divPlaceholderContent);
            }
        });
    })(jQuery);

    // tilføjer til DOM
    slide.appendChild(divTextBox);
}

/**
 * Vi sætter vores focusedEl-variabel til det sidste activeEl for at kunne gemme det og sætte stylingen på det,
 * når vi trykker på stylingsknapperne
 * */
function setAsFocusedEl(el){
    focusedEl = el;

    showStylingUsed(el);
}

// TODO når der er et element som ikke focussed mere
// == når focussedEl.classList.contains("dragAndResizeTextBoxContainer") || focussedEl.classList.contains("dragAndResizeContainer")
// vis styling -> else: slet btn-used fra alle styling-knapper-elementer
/**
 * Viser hvilke stylingsknappet vi har brugt på det focussed element
 * */
function showStylingUsed(el){

    // vi henter alle elementer med btn-used klassen
    const allElementsWithBtnUsed = document.getElementsByClassName("btn-used");
    // vi fjerner btn-used-klassen fra ALLE elementerne
    allElementsWithBtnUsed.forEach(el => el.classList.remove("btn-used"));

    // hvis det er en textBox
    if(el.classList.contains("dragAndResizeTextBoxContainer")){

        setStyling("isBold", btnBold);
        setStyling("isItalic", btnItalic);
        setStyling("isUnderlined", btnUnderline);

        setStyling("isMarginLeft", btnMarginLeft);
        setStyling("isMarginCentre", btnMarginCentre);
        setStyling("isMarginRight", btnMarginRight);
        setStyling("isList", btnList);

    } else{ // er det billede eller video og det har KUN fullscreen-knap-løsningen
        setStyling("isFullscreen", btnFullscreen);

    }


    setStyling("isFullscreen", btnFullscreen);


    function setStyling(styling, stylingElement){
        if(el.classList.contains(styling)){
            stylingElement.classList.add("btn-used");
        }
    }



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
        imgNewImage.title = file.name;


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

        // så et nyt billede automatisk kommer ind som det forreste
        addFocusAndZIndex(divImageContainer);

    }
};

function printPosition(el){
    console.log("top: " + el.style.top +
        " left: " + el.style.left +
        " width: " + el.offsetWidth +
        " height: " + el.offsetHeight);
}

function makeFullScreen(el) {
    const width = el.offsetWidth;
    const height = el.offsetHeight;

    if(width === 1120 || height === 630){
        // gør 80%
        el.style.width = width * 0.8 + "px";
        el.style.height = height * 0.8 + "px";
    }

    else {
        const widthRatio = 1120 / width;
        const heightRatio = 630 / height

        // Math.min = Finder den midste af de to ratios
        let ratio = Math.min(widthRatio, heightRatio);

        el.style.width = width * ratio + "px";
        el.style.height = height * ratio + "px";

        if(width > height) {
            el.style.left = "0px";
        } //Hvis height er størst eller billedet er kvadratisk
        el.style.top = "0px";
    }
}

function addFocusAndZIndex(el){

    setAsFocusedEl(el);

    // laver array af alle z-index
    const zIndexes = imageContainers.map(imageCon => Number(imageCon.style.zIndex));

    // finder største z-index
    const biggestZ = zIndexes.max();

    el.style.zIndex = biggestZ + 1;
    el.focus();

}

function deleteElement(el){
    jQuery(el).fadeOut(function(){el.remove();});

}

















