# Unified Messenger API
## Features and examples are still in draft

![Badge](https://img.shields.io/badge/Unified-Messenger%20API-green) ![SpringBoot](https://img.shields.io/badge/Spring%20Boot-2.5.3-brightgreen)

Unified Messenger API is a Spring Boot-powered RESTful service designed to combine various messenger platforms into a single API interface. With this API, developers can seamlessly integrate and manage multiple messaging platforms without the overhead of handling each messenger's specific intricacies.

## Features

- **Unified Interface**: Single endpoint to handle messages from and to different messenger platforms.
- **Supported Messengers**: WhatsApp, Telegram, Signal, and more! (Open to community contributions).
- **Extensibility**: Easily plug in more messengers as modules.

## Prerequisites

- Java 17 or later.
- Gradle.
- (Optional) Docker for containerized deployment.

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/unified-messenger-api.git
   ```

2. **Navigate to the project directory**
   ```bash
   cd unified-messenger-api
   ```

3. **Build and run the application**
   ```bash
   gradle clean build
   gradle bootRun
   ```

4. Open your browser or use a tool like [Postman](https://www.postman.com/) to access the API at:
   ```
   http://localhost:8080/api/login
   ```

## Usage

**Send a Message**

```http
POST /api/sendMessage
```
Payload:
```json
{
    "platform": "whatsapp",
    "receiver": "+1234567890",
    "message": "Hello from Unified Messenger API!"
}
```

... (Add other API endpoint usages here)

## Contributing

We welcome contributions! If you're interested in enhancing the features, fixing bugs, or improving the documentation, feel free to fork the repository and create a pull request. For major changes, please open an issue first.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
