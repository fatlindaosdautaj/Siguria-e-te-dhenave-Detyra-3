# Encrypted Messaging Platform with RSA on a Client-Server Model

## 🔍 Overview
A secure messaging platform that enables encrypted communication between clients using RSA public-key cryptography. Built with a client-server architecture to demonstrate real-time encrypted message exchange over a network.

## 🎯 Objectives
- Implement RSA for secure messaging between clients.
- Demonstrate key generation, encryption, decryption.
- Ensure secure communication via a central server.
- Provide a basic and intuitive user interface.

## 📋 Requirements
- Python 3.8+
- Socket programming knowledge
- Basic understanding of RSA encryption
- Terminal/console for running client and server

## ⚙️ Technical Stack
- **Language:** Python
- **Encryption:** RSA (PyCryptodome or manual implementation)
- **Networking:** Python Sockets
- **Interface:** Console-based UI

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
- `server.py` – server-side implementation.
- `client.py` – client-side implementation.
- `README.md` – project documentation.
- Example key generation and encryption code.
- Sample test data or usage guide.

## 🧾 Example Console Output




## ✅ Evaluation Criteria
- Correctness of RSA implementation.
- Functionality of message encryption/decryption.
- Stability of client-server communication.
- Code clarity and documentation.
- Security and key handling best practices.

