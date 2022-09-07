Tools creato nella tesi Passamano, in questo tools per effettuare una corretta miniaturizzazione a causa della dialog come spiegato nella tesi Passamano
va tolto il meccanismo di automatizzazione del partizionamento, quindi la classe BuilderMain va modificata non facendo eseguire la riga 62 per fare ciò basta
renderla semplicemente un commento e nella classe HTMLGenerator non vanno fatte eseguire le righe 157-160 rendendole anch'esse un commento 
e va modificata la riga 163 nel seguente modo parserHTML.writePage(pagina,orderedPartition), 
Inoltre per effettuare il partizionamento dell'interfaccia Concessionaria basta prima eseguire tutto il processo di minaturizzazione,
 poi modificare il codice HTML generato della classe Concessionario, dividendo il div del frame principale in due div e ottenendo quindi due schermate.