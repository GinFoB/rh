// URL of PDF document


$(function() {

    var url = null;

    $.get("http://localhost:8080/cv/name", function( data ) {
        url = data;
        // Asynchronous download PDF
        PDFJS.getDocument(url).then(function(pdf) {
            // Using promise to fetch the page
            pdf.getPage(1).then(function(page) {
                var scale = 0.5;

                var viewport = page.getViewport(scale);

                var canvas = document.getElementById('the-canvas');
                var context = canvas.getContext('2d');
                canvas.height = 200;
                canvas.width = 400;

                var renderContext = {
                    canvasContext: context,
                    viewport: viewport
                };
                page.render(renderContext);
            });
        });

    });

});



