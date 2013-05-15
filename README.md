WBA2_Phase2
===========
## <a id="inhalt"/>Inhaltsverzeichnis
* [Projekt] (#p)



##Teilnehmer  
[Lisa Lang](https://github.com/lslang)  
[Ramona Lind](https://github.com/rlind)

## <a id="p"/>Projekt

###Idee
Es geht um eine Anwendung, in der Songs geteilt werden können. 
Man kann neue Songs hochladen, einsehen, und kommentieren.
Zudem können die Songs Genres verfasst werden. 

####Synchrone Datenübertragung
* Musik hochladen und bewerten
* Zuteilung in Genres, Künstler, Songs
* nach Genres, Künstler, Songs filtern

####Asynchrone Datenübertragung
* ausgewählte Infos abonnieren
* aktuelle Infos, Uploads und Kommentare beziehen


### Szenario
Ein User "Peter" lädt ein neues Lied von seinem Lieblingskünsler "XY" hoch und versieht es  mit einem Kommentar. 
User "Dennis" hat die Seite und den Kanal des Künstlers "XY" abonniert und bekommt so die Infos zu dem hochgeladenen 
Song und den zugehörigen Kommentaren und Bewetungen. 
Peter hat das Lied einem passendem Genre zugeordnet. User "Petra" findet durch den Genrefilter den neuen Song.


## <a id="p"/>Unterpunkte

Die Daten müssen zu den verschiedenen Unterpunkten gespeichert werden
###Filter/Suche
* Nutzer
* Title 
* Genre

###Musik
* Interpret
* Title
* Genre
* Erscheinungsdatum
* Timestamp
* Bewertung + Text

###Nutzer Account
* Name
* Anmeldedatum
* Kommentare
* Timestamp

## <a id="p"/>Kommunikationsablauf
Synchron alles beim Hochladen
Asynchron wenn ein Datensat aboniert ist und unter dieser Kategorie etwas hochgeladen, kommentiert oder anders
verändert wird, werden diese Daten dem Abonenten Asynchron überliefert. 
Dazu muss in einer Kategorie 


```xml
<musikseite>
  <user>                                  <!--complex type-->
		<name></name>                 <!--simple type, restriktion-->
		<date></date>
		<comments>                    <!--complex type-->
			<timestamp></timestamp>     
		</comments>
	</user>
	<songs>
		<interpret></interpret>
		<title></title>		      <!--simple type, restriktion-->
		<genre></genre>		      <!--simple type, optional-->
		<releasedate></releasedate>
		<description></description>
		<rate></rate>
	</songs>
	<filter>			      
		<nutzer></nutzer>	     
		<titel></titel>
		<stil></stil>
		<datum></datum>		
	</filter>
</musikseite>
```
Zu erst wurde sich ein XML Dokument erstellt um Punkte zu erstellen. Punkte sortieren, zuordnen

##<a id="s"/>REST

Ressourcen: User, Song, Interpret, Genre
Operationen/Methoden
<br>GET - Listet Ressourcen auf
<br>PUT	- Aktualisiert Ressourcen, erzeuge Infos zu bekannter Entität oder bekannter Ressource
<br>DELETE - Löscht Ressoure
<br>POST - Anlegen neuer Ressource, die anderer untergeordnet ist

/user
<br>GET - alle User listen
<br>PUT		
<br>DELETE
<br>POST - neuen User hinzufügen

/user/{id}
<br>GET	- listet Details
<br>PUT	- neue Details
<br>DELETE - löschen
<br>POST

/songs
<br>GET - alles Songs listen
<br>PUT
<br>DELETE
<br>POST - neuen Song hinzufügen

/songs/{id}
<br>GET - listet Details
<br>PUT	- neue Details
<br>DELETE - löschen
<br>POST

/genre
<br>GET - alle Genres anzeigen
<br>PUT		
<br>DELETE		
<br>POST		

/genre/{id}
<br>GET	- Details anzeigen
<br>PUT		
<br>DELETE - (Genres sind festgelegt)
<br>POST

/interpret
<br>GET	- listet alle Interpreten auf
<br>PUT		
<br>DELETE		
<br>POST - neuen Interpreten 

/interpret/{id}
<br>GET	- Details
<br>PUT	- neue Details
<br>DELETE - löscht Interpreten
<br>POST		

/genre/{id}/song
<br>GET	- listet alle Songs in dem Genre
<br>PUT		
<br>DELETE		
<br>POST	

/interpret/{id}/song
<br>GET - listet alle Songs des Interpreten
<br>PUT	
<br>DELETE
<br>POST

/user/{id}/song
<br>GET - listet alle Songs des Users
<br>PUT
<br>DELETE
<br>POST
