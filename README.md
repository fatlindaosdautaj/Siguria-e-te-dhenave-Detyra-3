# Encrypted Messaging Platform with RSA on a Client-Server Model

## 🔍 Overview
A secure messaging platform that enables encrypted communication between clients using RSA public-key cryptography. Built with a client-server architecture to demonstrate real-time encrypted message exchange over a network.

## 🎯 Objectives
- Implement RSA for secure messaging between clients.
- Demonstrate key generation, encryption, decryption.
- Ensure secure communication via a central server.
- Provide a basic and intuitive user interface.

## 📋 Requirements
- Java 17 or newer
- IntelliJ IDEA or another Java-compatible IDE
- Basic understanding of RSA encryption
- Terminal or console for running the client and server

## ⚙️ Technical Stack
- **Language:** Java
- **Encryption:** RSA using `java.security`
- **Networking:** Java Sockets (TCP)
- **Interface:** Console-based user interface

## 🔐 RSA Key Pair Generation
- Each client generates its own public and private RSA key pair upon initialization.
- Public keys are shared with the server for distribution.
- Private keys remain securely stored on the client-side.

## 📨 Messaging Encryption and Decryption
- Messages are encrypted with the recipient’s public key.
- Messages are decrypted by the recipient using their private key.
- Ensures confidentiality and protection from MITM attacks.

## 🖥️ Server Implementation
- Handles client connections and routing of encrypted messages.
- Stores public keys of connected clients.
- Does **not** decrypt or inspect any messages (zero-knowledge).

## 👤 Client Implementation
- Generates RSA key pairs on startup.
- Connects to server and sends public key.
- Encrypts outgoing messages using recipient’s public key.
- Decrypts incoming messages with its private key.

## 🔒 Secure Channel Establishment
- Public key exchange occurs during initial handshake.
- Optional feature: signing messages for authenticity.
- No symmetric keys involved — pure asymmetric RSA.

## 🧑‍💻 User Interface and Experience
- Text-based console UI.
- Prompts user to enter recipient and message.
- Displays incoming messages in real-time.

## 📦 Deliverables
- `client/MainClient.java` – client-side entry point
- `client/MessageSender.java` – handles user input and encryption
- `client/MessageReceiver.java` – listens for incoming messages and decrypts
- `server/MainServer.java` – server entry point
- `server/ClientHandler.java` – per-client thread manager
- `rsa/RSAUtils.java` – RSA key generation, encryption, and decryption
- `rsa/KeyExchange.java` – public key management
- `tests/RSATests.java` – standalone test of RSA encryption logic
- `README.md` – this documentation

## 🧾 Example Console Output

![Image](https://github.com/user-attachments/assets/5f36cefd-3e71-4139-a081-2c99a9f3d7e4)

![Image](https://github.com/user-attachments/assets/f7d34586-b432-4102-8441-5a180ac1ec41)

![Image](https://github.com/user-attachments/assets/8078dca6-d6cb-4117-808c-3c3521e8d8a3)



## ✅ Evaluation Criteria
- Correctness of RSA implementation.
- Functionality of message encryption/decryption.
- Stability of client-server communication.
- Code clarity and documentation.
- Security and key handling best practices.

## 👥 Contributors
- Fatlinda Osdautaj
- Fioni Islami
- Festim Gashi
- Fatos Rama
