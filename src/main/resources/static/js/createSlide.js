const canvas = document.getElementById("canvas");

// henter alle knapper på siden
// TODO hent også buttons fra dropdowns/giv dem id
const inpTitle = document.getElementById("inpTitle");

const btnTextBox = document.getElementById("btnTextBox");
const btnImage = document.getElementById("btnImage");
const btnVideo = document.getElementById("btnVideo");

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

const btnSave = document.getElementById("btnSave");

let title;

const url = `http://localhost:8081/createSlide/saveSlide`;

btnSave.addEventListener('click', saveSlide);

function saveSlide(){
    console.log("Title i saveSlide: " + title);
    retrieveInput();

    if(title != null){
        const body = createJSONSlide(title);

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // betyder == vi sender et json i string-format
            },
            body: body
        };

        fetch(url, requestOptions)
            .then(data => checkIfSuccess(data))
            .catch(error => console.log("Fejl i fetch, createSlide.js: ", error));
    }
    else{
        alert("Indtast venligst en title.");
    }

}

function retrieveInput(){
    title = inpTitle.value;
    console.log("Title er her: " + title);

}

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

function titleError(){
    alert("Der findes allerede et slide med denne title, vælg venligst en anden.");
}

function checkIfSuccess(response){
    if(response.status >= 200 && response.status < 300){

        const cookie = document.cookie;

        alert(cookie);

        window.location.replace("/{}");
    }
    else if(response.status === 409){
        alert("Der findes allerede et slide med denne title, vælg venligst en anden.");
    }
    // else bliver den catchet i fetch
}

function createTextBox(){

}


const canvasContext = canvas.getContext("2d");
canvasContext.font = "30px Arial";
canvasContext.strokeText("Hello World",10,50);


const canvasContext1 = canvas.getContext("2d");
canvasContext1.font = "60px Arial";
canvasContext1.strokeText("Heeeeej",30,90);









