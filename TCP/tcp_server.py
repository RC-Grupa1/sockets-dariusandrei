import socket

HOST = "0.0.0.0"
PORT = 5100

def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    server_socket.bind((HOST, PORT))
    server_socket.listen(1)

    print(f"[TCP SERVER] Ascult pe portul {PORT}...")
    print("[TCP SERVER] Aștept conexiunea clientului...")

    conn, addr = server_socket.accept()
    print(f"[TCP SERVER] Client conectat de la {addr}")

    with conn:
        while True:
            data = conn.recv(1024)

            if not data:
                print("[TCP SERVER] Clientul a închis conexiunea")
                break

            message = data.decode("utf-8").strip()
            print(f"Client: {message}")

            if message.lower() == "exit":
                print("[TCP SERVER] Clientul a trimis exit. Închid conexiunea.")
                break

            reply = input("Server: ")
            conn.sendall((reply + "\n").encode("utf-8"))

            if reply.lower() == "exit":
                print("[TCP SERVER] Ai trimis exit. Închid conexiunea.")
                break

    server_socket.close()
    print("[TCP SERVER] Socket închis.")

if __name__ == "__main__":
    main()