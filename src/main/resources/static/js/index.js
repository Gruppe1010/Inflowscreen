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
        .then(slides => slides.forEach(showSlides))
        .catch(error => console.log("error: ", error));
}

getAllSlidesByTitle();

function showSlides(slides){

    inpIndexId++;
    console.log(slides.active);
    console.log(slides.activeSlideOrder);
    const liIndex = document.createElement('li');
    liIndex.classList.add("btn", "btn-dark", "li-width", "ui-sortable-handle");


    const spanIndex = document.createElement('span');
    spanIndex.classList.add("bi");
    spanIndex.classList.add("bi-arrow-down-up");

    const inpIndex = document.createElement('input');
    inpIndex.setAttribute("id", "flexSwitchCheckDefault" + inpIndexId)
    inpIndex.classList.add("form-check-input");
    inpIndex.type = 'checkbox';
    inpIndex.checked = slides.active;

    const labelIndex = document.createElement('label');
    labelIndex.classList.add("custom-checkbox");
    labelIndex.setAttribute('for', "flexSwitchCheckDefault" + inpIndexId)

    const deleteIndex = document.createElement('a');
    deleteIndex.classList.add("delete");
    deleteIndex.setAttribute('href', "gør til noget smart");

    labelIndex.value = slides.title;



    liIndex.appendChild(deleteIndex);
    liIndex.appendChild(labelIndex);
    liIndex.appendChild(inpIndex);
    liIndex.appendChild(spanIndex);
    ulSlide.appendChild(liIndex);
}



