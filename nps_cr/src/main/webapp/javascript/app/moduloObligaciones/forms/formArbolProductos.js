$(function() {
    initInicio();
    initArbolActividades();
  
});
function initArbolActividades(){
    $("#divArbolActividades").fancytree({
        checkbox: true,
        selectMode: 3,
        //source:treeData,
        select: function(event, data) {
            var selKeys = $.map(data.tree.getSelectedNodes(), function(node){
                if(!node.folder){
                    return "{id:'"+node.key+"',nombre:'"+node.title+"'}";
                }
            });
            
        },
        keydown: function(event, data) {
            if( event.which === 32 ) {
                data.node.toggleSelected();
                return false;
            }
        },
        //las sgtes lineas opcionales, son requeridas si existe mas de un arbol en una pagina:
        //initId: "treeData",
        cookieId: "fancytree-Cb1",
        idPrefix: "fancytree-Cb1-"
    });
    
    $("#divArbolActividades").fancytree("getRootNode").visit(function(node){
        node.setExpanded(true);
    });
    
}
function initInicio(){
    
}