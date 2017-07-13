var principal = (function() {
    // Private variables / properties
    var path = "pages/nps/principal";

    // Public Methods
    function iniciarMenus() {
        $("#menubar li a.menuItem").each(function(index) {
            var $textoIcono = $(this).html();
            var $ruta = $(this).attr("href");
            var $imagen = $(this).attr("image");
            var $div = $("<div class='iconoMenu'>" +
                    "<a href='" + $ruta + "'>" +
                    "<img src='" + $imagen + "' alt='" + $textoIcono + "'></img>" +
                    "</a>" +
                    "<span>" +
                    "<a href='" + $ruta + "'>" + $textoIcono + "</a>" +
                    "</span>" +
                    "</div>");
            $div.appendTo($("#contenedorIconos"));
        });
        var $height = 0;
        $(".iconoMenu").each(function(index) {
            var $thisHeight = parseInt($(this).height()) + 10;
            $heightInt = parseInt($height);
            if ($thisHeight > $heightInt) {
                $heightInt = $thisHeight;
            }
        });
        $(".iconoMenu").height($heightInt);
        $("#menubar").hide();
    }

    // Private Methods

    // Public API
    return {
        iniciarMenus: iniciarMenus
    };
})();

$(function() {
    principal.iniciarMenus();
});