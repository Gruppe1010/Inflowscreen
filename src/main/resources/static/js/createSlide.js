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

const imageForm = document.getElementById('imageForm');



// når der submittes
imageForm.addEventListener("submit", handleImageSubmit);
btnSave.addEventListener('click', saveSlide);

// når der trykkes på submit fanger vi dataen fra imageInputfelt og gemmer det på liste
function handleImageSubmit(){

}
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


// kaldes når man trykker på imageInputfelt - sørger for at submitte billedet
function imageAutoSubmit(input){

    // hvis der er noget i input-feltet for image
    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            document.imageForm.submit();

            /* TODO lav så den viser billedet i canvaset
            $('#blah')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
        };
        reader.readAsDataURL(input.files[0]);

             */
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










const canvasContext = canvas.getContext("2d");
canvasContext.font = "30px Arial";
canvasContext.strokeText("Hello World",10,50);


const canvasContext1 = canvas.getContext("2d");
canvasContext1.font = "60px Arial";
canvasContext1.strokeText("Heeeeej",30,90);










