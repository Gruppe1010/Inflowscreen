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
        .then(IndexSlideDTO => IndexSlideDTO.forEach(showSlides))
        .catch(error => console.log("error: ", error));
}

getAllSlidesByTitle();

function showSlides(IndexSlideDTO){

    inpIndexId++;

    const liIndex = document.createElement('li');
    liIndex.classList.add("btn", "btn-dark", "li-width", "ui-sortable-handle");


    const spanIndex = document.createElement('span');
    spanIndex.classList.add("bi");
    spanIndex.classList.add("bi-arrow-down-up");

    const inpIndex = document.createElement('input');
    inpIndex.setAttribute("id", "flexSwitchCheckDefault" + inpIndexId)
    inpIndex.classList.add("form-check-input");
    inpIndex.type = 'checkbox';
    inpIndex.checked = slides.isActive;

    const labelIndex = document.createElement('label');
    labelIndex.classList.add("custom-checkbox");
    labelIndex.setAttribute('for', "flexSwitchCheckDefault" + inpIndexId)

    labelIndex.innerText = IndexSlideDTO.title;




    inpIndex.appendChild(labelIndex);
    spanIndex.appendChild(inpIndex);
    liIndex.appendChild(spanIndex);
    ulSlide.appendChild(liIndex);
}



