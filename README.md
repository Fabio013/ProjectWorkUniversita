# 🎓 ProjectWorkUniversita

Piattaforma gestionale Java per ambiente universitario, derivata da un progetto legacy (2019) e **modernizzata gradualmente** come esercitazione back-end.  
Lo scopo è **trasformare un'applicazione datata in un'app moderna, ben organizzata e facilmente estendibile**, nel contesto di un corso di studi Java Back-End.

---

## 📌 Obiettivi del progetto

✅ Correggere bug e refusi nel codice legacy  
✅ Organizzare i package e i moduli secondo best practice  
✅ Rifattorizzare classi e servlet con pattern coerenti  
✅ Migliorare l'esperienza utente (UI, JSP, validazioni)  
✅ Integrare Git, GitHub e gestione dei branch per mostrare padronanza del flusso di sviluppo  
✅ Documentare ogni passo per usarlo come **portfolio personale**

---

## 🛠️ Tecnologie e strumenti

- **Java 21**
- **Eclipse IDE 2025-03**
- **Tomcat 11**
- **MySQL 8.x + Workbench**
- **JSP, Servlet, HTML5**
- **Git + GitHub**  
  (con branch `main`, `dev`, `feature/*`, `refactor/*`, `bugfix/*`, `docs/*`)
  
---

## 🧱 Struttura del progetto (aggiornata)

```text
src/
└── main/
    ├── java/
    │   └── com/universita/gestionale/
    │       ├── controller/
    │       │   ├── LoginServlet.java
    │       │   ├── PrenotaServlet.java
    │       │   └── StampaStudentiServlet.java
    │       ├── dao/
    │       │   └── Connessione.java
    │       ├── model/
    │       │   └── Prenotazione.java
    │       └── test/
    │           └── TestConnessione.java
    └── webapp/
        ├── index.jsp
        ├── studente.jsp
        ├── professore.jsp
        ├── logout.jsp
        └── WEB-INF/
            └── web.xml
```

---

## 🗂️ Struttura Git adottata

```markdown
Branch         | Scopo
-------------- | ----------------------------------------------
main           | Codice stabile e rilasci ufficiali
dev            | Codice in lavorazione
docs/*         | Documentazione, readme, gitignore
feat/*         | Nuove funzionalità
refactor/*     | Ristrutturazione classi / struttura progetto
bugfix/*       | Correzione di errori
```
  
---

## 📘 Stato attuale

```markdown
🔹 Analisi tecnica e setup ambiente completati  
🔹 Importato e collegato il database MySQL (`dump.sql`)  
🔹 Prime refactor su struttura package e servlet completati  
🔹 Fix bug "null" su login studente/professore  
🔹 Intestazioni refactor aggiunte ai file JSP  
🔸 In corso: strutturazione DAO e miglioramento sicurezza password
```

---

## ▶️ Avvio del progetto in locale

Clona il repository:

```bash
git clone https://github.com/Fabio013/ProjectWorkUniversita.git
```

Apri il progetto in Eclipse (Import > Existing Projects)

Configura il server:

- Apache Tomcat 11
- Imposta `webapp/` come cartella di contenuti Web

Importa il database:

- Apri `dump.sql` in MySQL Workbench
- Esegui lo script per creare il DB e le tabelle

Avvia il progetto:

```url
http://localhost:8080/ProjectWorkUniversita
```

✅ Login di test disponibili all’interno del dump SQL (utenti "studente" e "professore")

---

## 🔄 Evoluzione del progetto

Il progetto nasce da una base legacy (2019), con codice non strutturato e logiche hardcoded.

Interventi già effettuati:

✅ Riorganizzazione pacchetti (`controller`, `dao`, `model`, `test`)  
✅ Refactor servlet e naming coerente (`LoginServlet`, ecc.)  
✅ Pulizia JSP e gestione sessione con `null-check` robusti  
✅ Introduzione refactor header nei file JSP  
✅ Branch Git strutturati in modo professionale

Il refactoring proseguirà in modo incrementale, seguendo i principi della **Clean Architecture** e delle **best practice Java**.

---

## 👨‍💻 Autore

👤 Fabio013  
🔗 [GitHub Profile](https://github.com/Fabio013)  
📘 Progetto personale per portfolio e pratica reale con Java enterprise

  