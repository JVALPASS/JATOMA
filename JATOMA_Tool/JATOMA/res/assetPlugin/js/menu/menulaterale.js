
        function openNav() {
            document.getElementById("myNav").style.width = "70%";
        }

        function closeNav() {
            document.getElementById("myNav").style.width = "0%";

        }



        function insert_menu(li){
            ul = li.childNodes[1];

                if(event.eventPhase == Event.AT_TARGET){
                    if(ul.hasAttribute("hidden")){
                        ul.removeAttribute("hidden");
                    }
                    else{
                        ul.setAttribute("hidden",true);

                }
        }


    }

    //var pElems = document.getElementsByTagName("li");
    var pElems = document.querySelectorAll(".subnav");

    for (var i = 0; i < pElems.length; i++) {

        pElems[i].addEventListener("click", handleMouseOver);
        //pElems[i].addEventListener("mouseout", handleMouseOut);
    }

   function handleMouseOver(e) {
            if(e.eventPhase == Event.AT_TARGET){

                closeNav();
            }
    }

