Tools creato nella tesi Trezza, in questo tools per effettuare una corretta miniaturizzazione a causa della dialog come speigato nella tesi Passamano
va tolto il meccanismo di automatizzazione del partizionamento, quindi la classe BuilderMain va modificata non facendo eseguire la riga 62 per fare ciò basta
renderla semplicemente un commento e nella classe HTMLGenerator non vanno fatte eseguire le righe 157-160 rendendole anch'esse un commento 
e va modificata la riga 163 nel seguente modo parserHTML.writePage(pagina,orderedPartition), 
