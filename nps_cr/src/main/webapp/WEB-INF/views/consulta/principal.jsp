<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template pageTitle="Bienvenido" scrollPanelTitle="">
    
    <jsp:attribute name="headArea">
        <script type="text/javascript" src="/nps/javascript/app/principal.js" />" charset="utf-8"></script>
        
        <style>
            .iconoMenu{
                    float: left;
                    overflow: hidden;
                    width: 200px;
                    margin: 5px 10px;
                    text-align: center;				
            }
            .iconoMenu img{
                    width: 100px;
                    height: 100px;
                    border-width:0px;
            }
            .iconoMenu span{
                    display: block;
                    text-transform:capitalize;
                    text-align: center;		
                    font-weight:bold;
                    font-size: 1em;					
            }
            .iconoMenu span a{
                    text-decoration: none;						
            }
            .iconoMenu:hover{
                    background-color:#CBE3F7;							
            }
            #contenedorIconos {	
                    margin: 20px auto;
                    max-width: 800px;
                    overflow: hidden;	
                    display: table;
            }
        </style> 
    </jsp:attribute>
    
    <jsp:attribute name="bodyArea">
        <div class="tac titua">CONSULTAS</div>
        <ul id="menubar">  
            <li> <a data-icon="ui-icon-document"></a>  
                <ul>   
                    <!--<li><a class="menuItem" image="/nps/images/requirement.png" href="./pages/consulta/requisitos/menu"><p>REQUISITOS</p></a></li>-->
<!--                     <li><a class="menuItem" image="/nps/images/requirement.png" href="/nps/pages/consulta/requisitos/opcion3"><p>REQUISITOS</p></a></li> -->
					<li><a class="menuItem" image="/nps/images/requirement.png" href="/nps/pages/consulta/consultaRequisitos"><p>REQUISITOS</p></a></li>
                    <li><a class="menuItem" image="/nps/images/obligation.png" href="/nps/inicio.jsp"><p>OBLIGACIONES</p></a></li>
                    <!--<li><a class="menuItem" image="/nps/images/obligation.png" href="./pages/consulta/obligaciones/menu"><p>PRUEBAS</p></a></li>-->
                </ul>  
            </li>  
        </ul>
       
        <div class="scrollPanel" id="scrollPrincipal">		
            <span id="contenedorIconos">				
            </span>		
        </div>
    </jsp:attribute>
    
</t:template>