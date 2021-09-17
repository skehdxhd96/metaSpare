function setThumbnail(event) {
    const reader = new FileReader();
    reader.onload = function(event) {
        const img = document.querySelector("#fileImg");
        img.setAttribute("src", event.target.result);
    };
    reader.readAsDataURL(event.target.files[0]);
}