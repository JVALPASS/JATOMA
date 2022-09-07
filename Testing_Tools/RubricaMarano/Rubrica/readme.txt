Tools creato nella tesi Marano
Per effettuare il partizionamento o lo si effettua manualmente separando il file HTML generato in piu div, o autamaticamemte come nella tesi Marano
con l'algoritmo, per fare ciò per prima cosa gli oggetti del codice sorgente da minaiturizzare devono avere lo stesso nome dell'id assegnato ad esso
(l'id lo si può settare manualmente con il metodo setName), inoltre bisogna far eseguire nella classe Buildermain la riga 61, 
nella classe HTMLGenerator vanno fatte eseguire le righe 157-160 e modificare la riga 163 nel seguente modo parserHTML.writePage(pagina,newOrderedPartition), 
inoltre vanno inserite le regole di partizionamento all'interno del metodo partition.