[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/UwOds2hL)


# Laborator Sockets - TCP și UDP

## Membrii echipei

- Darius Pop: Server Python
- Marian Andrei: Client Java

## Misiunea A - Chat Fiabil TCP

Pentru Misiunea A am implementat o aplicație de chat de tip Ping-Pong folosind protocolul TCP.

Serverul este scris în Python și ascultă pe portul 5000. Clientul este scris în Java și se conectează la adresa IP a serverului și la portul 5000.

Fluxul aplicației este:
1. Serverul pornește și așteaptă o conexiune.
2. Clientul se conectează la server.
3. Clientul trimite primul mesaj.
4. Serverul primește mesajul, îl afișează și trimite un răspuns.
5. Conversația continuă secvențial.
6. Dacă unul dintre utilizatori trimite mesajul `exit`, socket-ul se închide.

TCP este orientat pe conexiune. Înainte de transmiterea datelor, se realizează handshake-ul:
SYN, SYN-ACK, ACK.

Mesajele sunt codificate folosind UTF-8.

## Misiunea B - Chat Rapid UDP

Pentru Misiunea B am rescris chat-ul folosind protocolul UDP.

Serverul este scris în Python și ascultă pe portul 5001. Clientul este scris în Java și trimite datagrame către server.

UDP nu stabilește o conexiune. Serverul folosește funcția `recvfrom`, prin care primește atât mesajul, cât și adresa IP și portul clientului. După primul pachet primit, serverul știe unde trebuie să trimită răspunsurile.

Fluxul aplicației este:
1. Serverul pornește și așteaptă pachete UDP.
2. Clientul trimite primul mesaj către server.
3. Serverul extrage adresa IP și portul clientului.
4. Serverul trimite răspunsul folosind `sendto`.
5. Conversația continuă până când unul dintre utilizatori trimite `exit`.

Mesajele sunt codificate folosind UTF-8.

## Diferențe observate între TCP și UDP

| Caracteristică | TCP | UDP |
|---|---|---|
| Tip PDU | Segment | Datagramă |
| Conexiune | Da | Nu |
| Handshake | Prezent: SYN, SYN-ACK, ACK | Absent |
| Confirmare primire | ACK automat | Nu există ACK automat |
| Funcții folosite | listen, accept, recv, send | recvfrom, sendto |
| Fiabilitate | Mai mare | Nu garantează livrarea |

## Capturi Wireshark
TCP: 

<img width="2519" height="1458" alt="Screenshot 2026-04-30 111308" src="https://github.com/user-attachments/assets/2e0f6758-78b1-4983-9182-adf3652b7fb7" />


UDP:

<img width="2520" height="1441" alt="Screenshot 2026-04-30 113951" src="https://github.com/user-attachments/assets/fdd21a43-70d4-4500-8321-1570b16eae37" />


### TCP

Filtru folosit:

```text
tcp.port == 5100
udp.port == 5101
