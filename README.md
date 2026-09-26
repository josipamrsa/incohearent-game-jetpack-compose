# Incohearent Game App

A digital version of the popular **Incohearent** card game, built with **Jetpack Compose** for Android client and **Ktor** for the backend. This app brings the fun and hilarity of the physical game to your mobile devices, allowing players to enjoy the game remotely with friends or by themselves.

## About Incohearent
**Incohearent** is a party game that challenges players to decipher phrases made of incoherent, gibberish-like words into understandable phrases or expressions. Each card contains a seemingly nonsensical set of words, but when spoken aloud, the real phrase begins to emerge.

Players have a limited time to guess the correct phrase based on phonetic clues. The game is fun for gatherings, whether you're a word game enthusiast or just looking to laugh out loud with friends.

## App Features

- **TBD**

## Tech Stack

This app was developed using a combination of modern technologies to ensure a smooth and enjoyable experience.

### Frontend (Client)

- **Jetpack Compose**: The app's UI is built with Jetpack Compose, offering a fully declarative, flexible, and maintainable Android UI framework. It allows for faster development with less boilerplate code.
  
### Backend

- **Ktor**: The backend of the application is powered by Ktor, a modern Kotlin framework for building asynchronous servers and clients. It keeps players connected over **WebSockets** and synchronizes the game state between them in real time.

### Shared protocol

- The client and the server talk over a WebSocket at `/lobby`. Every message is a JSON object with a `type` field, e.g. `{"type":"LOG_NEW_PLAYER","player":{...}}`. All message types are defined once in the `protocol` module and used by both sides.

## Project Structure

| Module | Description |
|---|---|
| `app` | Android app: Jetpack Compose UI, ViewModels, navigation, DI |
| `domain` | Pure Kotlin models, repository interfaces and use cases |
| `data` | Realtime client and repository implementations |
| `protocol` | Messages exchanged between client and server (shared) |
| `server` | Ktor server |

## Getting Started

To get started with the project, follow these steps:

### Prerequisites

- **Android Studio** (includes the JDK 17 it needs). The whole project, server included, is one Gradle build.

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/josipamrsa/incohearent-game-jetpack-compose.git
    ```

2. Open the project in Android Studio.

3. Start the server with the **Server** run configuration, or from the terminal:

    ```bash
    ./gradlew :server:run
    ```

    It listens on port `8080`.

4. Point the app at the server: set `BASE_SOCKET_URL` in `data/build.gradle.kts` to your computer's address on the local network (e.g. `ws://192.168.1.10:8080`).

5. Build and run the `app` configuration on your Android device or emulator.

### Building the server for deployment

```bash
./gradlew -PserverOnly :server:buildFatJar
java -jar server/build/libs/server-all.jar
```

`-PserverOnly` builds only the `server` and `protocol` modules, so no Android SDK is needed.

## How to Play

1. **TBD**

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
