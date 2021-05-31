$( function() {
    $( "#sortable" ).sortable();
    $( "#sortable" ).disableSelection();
} );


const ulSlide = document.getElementById('sortable');
let inpIndexId = 0;

function getAllSlidesByTitle() {
    const url = `http://localhost/api/slides/${orgId}`; // localhost
    //const url = `http://inflowscreen.dk/api/slides/${orgId}`; // online


    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json', // betyder == vi sender et json i string-format
        },
        redirect: 'follow'
    };

         fetch(url, requestOptions)
        .then(response => response.json())
        .then(slides => slides.forEach(showSlide))
        .catch(error => console.log("error: ", error));
}

getAllSlidesByTitle();

function showSlide(slide){

    inpIndexId++;
    console.log(slide.active);
    console.log(slide.activeSlideOrder);
    const liIndex = document.createElement('li');
    liIndex.setAttribute('id', `liIndex${slide.id}`);
    liIndex.classList.add("btn", "btn-dark", "li-width", "ui-sortable-handle");

    const titleIndex = document.createElement('a');
    titleIndex.classList.add("slide-title");
    titleIndex.setAttribute('href', `editSlide/${slide.id}`);
    titleIndex.innerText = slide.title;

    const inpIndex = document.createElement('input');
    inpIndex.setAttribute("id", "flexSwitchCheckDefault" + inpIndexId)
    inpIndex.classList.add("form-check-input");
    inpIndex.type = 'checkbox';
    inpIndex.checked = slide.active;

    const labelIndex = document.createElement('label');
    labelIndex.classList.add("custom-checkbox");
    labelIndex.setAttribute('for', "flexSwitchCheckDefault" + inpIndexId)

    const deleteIndex = document.createElement('a');
    deleteIndex.classList.add("delete");
    deleteIndex.addEventListener('click', function(){deleteSlide(slide.id)});


    liIndex.appendChild(deleteIndex);
    liIndex.appendChild(labelIndex);
    liIndex.appendChild(inpIndex);
    liIndex.appendChild(titleIndex);
    ulSlide.appendChild(liIndex);
}

function deleteSlide(slideId){
    const url = `http://localhost/api/slide/${slideId}`; // localhost
    //const url = `http://inflowscreen.dk/api/slide/${slideId}`; // online


    const requestOptions = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json', // betyder == vi sender et json i string-format
        },
        //redirect: 'follow'
    };

    function checkIfSuccess(response, slideId){
        if(response.status >= 200 && response.status < 300){
            document.getElementById(`liIndex${slideId}`).remove();
        }
        else if(response.status === 409){
            alert("Der er gået noget galt");
        }
        // else bliver den catchet i fetch
    }
    fetch(url, requestOptions)
        .then(data => checkIfSuccess(data, slideId))
        .catch(error => console.log("Fejl i fetch, index.js: ", error));
}


