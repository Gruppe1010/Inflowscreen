// TODO teksbokst
//      - Sørg for at valgte stylingknapper i baren ikke vises, hvis der IKKE er en textBox i focus
//      - set slet-kryds INDE i kassen
//      - fiks bug med at dropdowns står efter det sidste element - det skal sættes tilbage til original, når et el kommer i focus
//      - tilføj andre anderledes skrifttyper, som er lidt anderledes
//      - Ret i color-dropdown at man ikke kan se sort - skriv muligvis bare valgmulighederne i hvis allesammen.
//              ELLERS ændre i stylingknapper så de bliver hvide og de 3 ting man kan tilføje bliver sorte??

// TODO andre
//      - gør GEM-knap grøn
//      - overvej at gøre så man ikke kan se de andre valgmuligheder hvis man står på et img-el og omvendt


// TODO billeder
//      - ret bug med at man ikke kan tilføje samme billede igen (+ heller ikke hvis man sletter det)

// TODO gør så den ikke siger fejl ved titel ved samme id

async function getSlideToEdit(){
    const url = `http://localhost/api/slide/${slideId}`; // localhost
    //const url = `http://inflowscreen.dk/api/slide/${slideId}`; //online

    /*
    let title = inpTitle.value;

    // hvis de har indtastet en titel
    if(title.length > 0){

        const textBoxes = convertTextBoxesToJSON();

        // TODO tilføj noget async
        const images = convertImagesToJSON();


        let slide = {
            "title": title,
            "slideImageDTOs": images,
            "textBoxDTOs": textBoxes
        }
        */

    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json', // betyder == vi sender et json i string-format
        },
        redirect: 'follow'
    };

    return await fetch(url, requestOptions)
        .then(response => response.json())
        .catch(error => console.log("Fejl i fetch, createSlide.js: ", error));
}

function displaySlideToEdit(slide){



}

getSlideToEdit()
    .then(slide => displaySlideToEdit(slide));






//----------------------------------------------- FRA CREATESLIDE


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
btnBold.addEventListener('mousedown', function (e){
    e.preventDefault();
    setToBold();
    showStylingUsed();
});
function setToBold(){
    // vi tjekker at focusedEl er en textBox
    if(focusedEl.classList.contains("textArea")){
        // hvis den allerede er bold
        if(focusedEl.classList.contains("isBold")){
            focusedEl.classList.remove("isBold");
            focusedEl.style.fontWeight = "normal";
        } else{ // hvis den IKKE er bold
            focusedEl.classList.add("isBold");
            focusedEl.style.fontWeight = "bold";
        }

    }
}

const btnItalic = document.getElementById("btnItalic");
btnItalic.addEventListener('mousedown', function (e){
    e.preventDefault();
    setToItalic();
    showStylingUsed();
});
function setToItalic(){
    // Vi tjekker at focusedEl er en tekstbox
    if(focusedEl.classList.contains('textArea')){

        if(focusedEl.classList.contains("isItalic")){
            focusedEl.classList.remove("isItalic");
            focusedEl.style.fontStyle = "normal";
        } else {
            focusedEl.classList.add("isItalic");
            focusedEl.style.fontStyle = 'italic';
        }
    }
}

const btnUnderline = document.getElementById("btnUnderline");
btnUnderline.addEventListener('mousedown', function (e){
    e.preventDefault();
    setToUnderline();
    showStylingUsed();
});
function setToUnderline(){
    if(focusedEl.classList.contains('textArea')){

        if(focusedEl.classList.contains("isUnderlined")){
            focusedEl.classList.remove("isUnderlined");
            focusedEl.style.textDecoration = "none";
        } else {
            focusedEl.classList.add("isUnderlined");
            focusedEl.style.textDecoration = 'underline';
        }
    }

}

const btnTextColour = document.getElementById("btnTextColour");

const inpFont = document.getElementById("inpFont");
// inpFont.addEventListener('mousedown', function (e){e.preventDe();});

const btnFontSize = document.getElementById("btnFontSize");


const btnMarginLeft = document.getElementById("btnMarginLeft");
btnMarginLeft.addEventListener('mousedown', function (e){
    e.preventDefault();
    setToMarginLeft();
    showStylingUsed();
});
function setToMarginLeft(){
    if(focusedEl.classList.contains('textArea')){
        focusedEl.classList.add("isMarginLeft");
        focusedEl.style.textAlign = 'left';
        removeTwoClassesFromFocusedEL("isMarginRight", "isMarginCentre");
    }
}

function removeTwoClassesFromFocusedEL(class1, class2){
    focusedEl.classList.remove(class1);
    focusedEl.classList.remove(class2);
}


const btnMarginCentre = document.getElementById("btnMarginCentre");
btnMarginCentre.addEventListener('mousedown', function (e){
    e.preventDefault();
    setToMarginCentre();
    showStylingUsed();
});
function setToMarginCentre(){
    if(focusedEl.classList.contains('textArea')){
        focusedEl.classList.add("isMarginCentre");
        focusedEl.style.textAlign = 'center';
        removeTwoClassesFromFocusedEL("isMarginRight", "isMarginLeft");
    }
}

const btnMarginRight = document.getElementById("btnMarginRight");
btnMarginRight.addEventListener('mousedown', function (e){
    e.preventDefault();
    setToMarginRight();
    showStylingUsed();
});
function setToMarginRight(){
    if(focusedEl.classList.contains('textArea')){
        focusedEl.classList.add("isMarginRight");
        focusedEl.style.textAlign = 'right';
        removeTwoClassesFromFocusedEL("isMarginLeft", "isMarginCentre");
    }
}

const btnList = document.getElementById("btnList");
btnList.addEventListener('click', setToList);
function setToList(){}

const btnFullscreen = document.getElementById("btnFullscreen");
btnFullscreen.addEventListener('click', makeFullScreen);

// GEM-knap
const btnSave = document.getElementById("btnSave");
btnSave.addEventListener('click', saveSlide);


// ændre skifttypen i den aktive boks
const changeFontStyle = function (font) {
    if(focusedEl.classList.contains("textArea")){
        focusedEl.style.fontFamily = font.value;
    }
}
// ændre skiftstørrelsen i den aktive boks
const changeFontSize = function (font) {
    if(focusedEl.classList.contains("textArea")){
        focusedEl.style.fontSize = font.value + "px";
    }
}
// ændre skiftfarve i den aktive boks
const changeFontColor = function (font) {
    if(focusedEl.classList.contains("textArea")){
        focusedEl.style.color = font.value;
        document.activeElement.style.color = font.value;
    }
}

/**
 * Omdanner data på slide og sender i fetch
 * */
function saveSlide(){

    const url = `http://localhost/api/saveSlide`; // localhost
    //const url = `http://inflowscreen.dk/api/saveSlide`; //online

    let title = inpTitle.value;

    // hvis de har indtastet en titel
    if(title.length > 0){

        const textBoxes = convertTextBoxesToJSON();

        // TODO tilføj noget async
        const images = convertImagesToJSON();


        let slide = {
            "title": title,
            "slideImageDTOs": images,
            "textBoxDTOs": textBoxes
        }

        const body = JSON.stringify(slide);
        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json', // betyder == vi sender et json i string-format
            },
            body: body
        };

        // TODO tilføj måske noget async
        function checkIfSuccess(response){
            if(response.status >= 200 && response.status < 300){
                /* TODO har vi overhovedet brug for denne cookie??
                const cookie = document.cookie;

                alert(cookie);

                 */

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
/**
 * Omdanner alle textboxes fra list til JSON
 * */
function convertTextBoxesToJSON(){
    // returnerer en liste hvor hvert el på imageContainters-listen har undergået makeImageJSON-func
    return textBoxesOnSlide.map(div => makeTextBoxJSON(div));

    function makeTextBoxJSON(div){

        const textBox = div.getElementsByTagName('textarea')[0];

        let top =  div.style.top; // == "" eller 2px
        if(top.length > 1) top = top.substring(0, top.length - 2);
        let left = div.style.left;
        if(left.length > 1) left = left.substring(0, left.length - 2);


        // hvis de er 0 kommer de ud som "" - derfor sætter vi lige værdien til 0px
        // også < 0, hvis der nu er sket en fejl på en eller anden måde på slidet
        if(top === "" || top <= 0) top = "0";
        if(left === "" || left <= 0) left = "0";

        const fontSize = textBox.style.fontSize;



        return {
            "text": textBox.value,
            "top": top,
            "left": left,
            "width": div.offsetWidth, // TODO enten div eller textBox
            "height": div.offsetHeight, // TODO enten div eller textBox
            "isBold": textBox.classList.contains("isBold"),
            "isItalic": textBox.classList.contains("isItalic"),
            "isUnderlined": textBox.classList.contains("isUnderlined"),
            "isList": textBox.classList.contains("isList"),
            "font": textBox.style.fontFamily,
            "fontSize": fontSize.substring(0, fontSize.length - 2), // for at minusse px
            "fontColour": textBox.style.color,
            "margin": textBox.style.textAlign
        }
    }
}

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
        if(top.length > 1) top = top.substring(0, top.length - 2);
        let left = div.style.left;
        if(left.length > 1) left = left.substring(0, left.length - 2);

        // hvis de er 0 kommer de ud som "" - derfor sætter vi lige værdien til 0px
        // også < 0, hvis der nu er sket en fejl på en eller anden måde på slidet
        if(top === "" || top <= 0) top = "0";
        if(left === "" || left <= 0) left = "0";

        // TODO tilføj z-index (måske også en bool: isFullscreen)
        return {
            "top": top,
            "left": left,
            "width": img.offsetWidth,
            "height": img.offsetHeight,
            "zIndex": div.style.zIndex,
            "base64": img.src,
            "fileName": img.title
        }
    }
}

function deleteIcon(el){
    // TODO lav kryds til at forsvinde hvis ikke aktiv
    const spanDelete = document.createElement('span');
    spanDelete.classList.add("icon-delete", "ui-icon", "ui-icon-closethick");
    spanDelete.addEventListener('click',function(){deleteElement(el)});
    el.appendChild(spanDelete);
}



let calcTextBoxHeight;

// -------------- TEXTBOX
function addTextToSlide(){
    newTextBoxId++;

    // div-container
    const divId = "divTextBoxContainer" + newTextBoxId;
    const divTextAreaContainer = document.createElement('div');
    divTextAreaContainer.setAttribute('id', divId);
    divTextAreaContainer.classList.add("dragAndResizeTextBoxContainer");
    divTextAreaContainer.style.zIndex = 900000;



    const textBoxId = "textBox" + newTextBoxId;
    const textArea = document.createElement('textarea');
    textArea.setAttribute('id', textBoxId);
    textArea.setAttribute('contenteditable', "true");
    textArea.setAttribute('placeholder', 'Tryk her for at tilføje tekst');
    textArea.setAttribute('oninput', "calcTextBoxHeight(this);");

    // når man flytter på tekstboksen skal man også kunne enter direkte - ikke kun når man har inputtet noget
    divTextAreaContainer.addEventListener('mouseup', function(){calcTextBoxHeight(textArea);});

    calcTextBoxHeight = function(textAreaParam){

        // vi gør så man kan trykke enter
        $("#textBoxId").ready(function() {
            $('textarea').unbind('keypress');
        });

        textAreaParam.style.height = "";

        const textBoxId =  textAreaParam.id.match(/(\d+)/);

        const divTextBoxContainer = document.getElementById('divTextBoxContainer' + textBoxId[0]);

        let topString = divTextBoxContainer.style.top;

        let top = Number(topString.substring(0, topString.length - 2));

        if(textAreaParam.scrollHeight + 5 > 618 - top){
            textAreaParam.style.height = 618 - top + "px";

            // når boksen ikke skal være større, kan man ikke trykke enter mere - så vi slipper for scroll-bar
            $("#textBoxId").ready(function() {

                $('textarea').keypress(function(event) {

                    if (event.keyCode == 13) {
                        event.preventDefault();
                    }
                });
            });

        }else{
            textAreaParam.style.height = textAreaParam.scrollHeight + 5 + "px";
        }
    }



    //textArea.setAttribute('oninput','this.style.height = "";this.style.height = this.scrollHeight + 5 + "px"');
    textArea.classList.add("textArea", "isMarginLeft");
    textArea.addEventListener('click', function(){setAsFocusedEl(textArea);});
    btnMarginLeft.classList.add("btn-used");
    // standardstyling den oprettes med
    textArea.style.fontFamily = "Ariel";
    textArea.style.fontSize = "25px";
    textArea.style.color = "black";
    textArea.style.textAlign = "left";

    textBoxesOnSlide.push(divTextAreaContainer);


    //https://stackoverflow.com/questions/59958676/how-to-make-a-js-textarea-draggable
    $(function() {
        $('.dragAndResizeTextBoxContainer')
            .draggable({
                containment: "#slide",
                cancel: "divId",
                start: function (){
                    $('#divId').focus();
                } ,
                stop: function (){
                    $('#divId').focus();
                }
            })
            .resizable({
                containment: "#slide",
                handles: "e",
                autoHide: true // gemmer hive-firkanter når man ikke har musen over elementet
            });
    });



    // TODO lav kryds til at forsvinde hvis ikke aktiv
    deleteIcon(divTextAreaContainer);


    /*
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
    */

    // tilføjer til DOM
    divTextAreaContainer.appendChild(textArea);
    slide.appendChild(divTextAreaContainer);
}




/**
 * Vi sætter vores focusedEl-variabel til det sidste activeEl for at kunne gemme det og sætte stylingen på det,
 * når vi trykker på stylingsknapperne
 * */
function setAsFocusedEl(el){
    el.focus();
    focusedEl = el;

    showStylingUsed();
}

// TODO når der er et element som ikke focussed mere
// == når focussedEl.classList.contains("dragAndResizeTextBoxContainer") || focussedEl.classList.contains("dragAndResizeContainer")
// vis styling -> else: slet btn-used fra alle styling-knapper-elementer
/**
 * Viser hvilke stylingsknappet vi har brugt på det focussed element
 * */
function showStylingUsed(){

    // vi henter alle elementer med btn-used klassen
    const allElementsWithBtnUsed = document.querySelectorAll(".btn-used");

    // vi fjerner btn-used-klassen fra ALLE elementerne
    allElementsWithBtnUsed.forEach(el => el.classList.remove("btn-used"));

    // hvis det er en textBox
    if(focusedEl.classList.contains("textArea")){

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
        if(focusedEl.classList.contains(styling)){
            stylingElement.classList.add("btn-used");
        }
    }



}


// -------------- BILLEDE
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




        // tilføjer til DOM
        //imgNewImage.appendChild(spanDelete);
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
    if(focusedEl.classList.contains('dragAndResizeContainer')){

        const width = el.offsetWidth;
        const height = el.offsetHeight;

        if(width >= 1120 || height >= 630){
            // gør 80%
            el.style.width = width * 0.8 + 'px';
            el.style.height = height * 0.8 + 'px';
        }

        else {
            const widthRatio = 1120 / width;
            const heightRatio = 630 / height

            // Math.min = Finder den midste af de to ratios
            const ratio = Math.min(widthRatio, heightRatio);

            const newWidth = width * ratio;
            const newHeight = height * ratio;

            el.style.width = newWidth + 'px';
            el.style.height = newHeight + 'px';

            /* sådan her kan den ryge udenfor hvis den står for tæt på én af kanterne - pga. linjen efter if
            if(width > height) {
                el.style.left = "0px";
            } //Hvis height er størst eller billedet er kvadratisk
               el.style.top = '0px';
             */

            // TODO sig noget med hvis newWidth er noget, så sæt right til newWidth eller noget

            el.style.top = '0px';
            el.style.left = '0px';
        }
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

    alert("hej");
    // hvis det er en textbox
    console.log(el);
    if(el.classList.contains('dragAndResizeTextBoxContainer')){
        // fjern fra textBox array
        textBoxesOnSlide.remove(el);

        console.log("textel", el);
        //delete textBoxesOnSlide[textBoxesOnSlide.indexOf(el)];
    }
    else{ // er det en img
        // fjern fra img array
        imageContainers.remove(el);
        console.log("imgel", el);

        //delete imageContainers[imageContainers.indexOf(el)];

    }
    jQuery(el).fadeOut(function(){el.remove();});


}



















