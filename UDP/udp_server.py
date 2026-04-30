import socket

HOST = "0.0.0.0"
PORT = 5101

def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    server_socket.bind((HOST, PORT))

    print(f"[UDP SERVER] Ascult pe portul {PORT}...")
    print("[UDP SERVER] Aștept primul mesaj de la client...")

    client_address = None

    while True:
        data, address = server_socket.recvfrom(1024)

        if client_address is None:
            client_address = address
            print(f"[UDP SERVER] Client detectat: {client_address}")

        message = data.decode("utf-8").strip()
        print(f"Client: {message}")

        if message.lower() == "exit":
            print("[UDP SERVER] Clientul a trimis exit. Închid serverul.")
            break

        reply = input("Server: ")

        server_socket.sendto(
            reply.encode("utf-8"),
            client_address
        )

        if reply.lower() == "exit":
            print("[UDP SERVER] Ai trimis exit. Închid serverul.")
            break

    server_socket.close()
    print("[UDP SERVER] Socket închis.")

if __name__ == "__main__":
    main()