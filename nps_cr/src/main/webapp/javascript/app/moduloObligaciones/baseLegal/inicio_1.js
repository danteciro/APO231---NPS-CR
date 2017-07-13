var gestionBaseLegal = (function(){
    var path="pages/indice";
    //Inicializa Spinners
    //Inicializa Datepickers
    //Inicializa Toggles
    //Inicializa Botones para Arboles
    //Funciones
    function limpiarSimple(){
        $('#dialogBusquedaAvanzadaBaseLegal input[type=text]').each(function(index){
            $(this).val("");
        });
    }
    function botonesLimpiar(){
        $('#botoLimpiarBusquedaAvanzadaBaseLegal').click(function(){
            limpiarSimple();
        });
    }
    
    return{
      botonesLimpiar:botonesLimpiar  
    };
})
();
$(function(){
   gestionBaseLegal.botonesLimpiar(); 
});
