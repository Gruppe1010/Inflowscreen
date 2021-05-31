$( function() {
    $( "#sortable" ).sortable();
    $( "#sortable" ).disableSelection();
} );


const indexSlide = document.getElementById('sortable');


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

    const liIndex = document.createElement('li');
    const spanIndex = document.createElement('span');
    const inpIndex = document.createElement('input').type = 'checkbox';
    const labelIndex = document.createElement('label');

    labelIndex.innerText = slides.title;




    inpIndex.appendChild(labelIndex);
    spanIndex.appendChild(inpIndex);
    liIndex.appendChild(spanIndex);
    indexSlide.appendChild(liIndex);


}



