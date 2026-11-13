# Iterative Socket Server

A Java TCP client–server project developed for a Computer Networks course. The client requests system information from a Linux server and measures response times for batches of simultaneous requests.

The project demonstrates socket communication, client-side multithreading, server-side process execution, and response-time measurement.

## How It Works

1. The server listens on a user-selected TCP port.
2. The client prompts for the server's IPv4 address and port.
3. The user selects a command and a request count: **1, 5, 10, 15, 20, or 25**.
4. Each client thread opens a separate connection and sends the selected command number.
5. The server executes the corresponding system command, returns its standard output, and closes the connection.
6. The client displays the responses and timing results after all request threads finish.

The **client sends requests concurrently**, while the **iterative server handles one connection at a time**. This allows observation of how response times change as more requests compete for service. The server socket uses a requested connection backlog of 25; this is not a count of server worker threads.

## Available Commands

| Menu option | Information requested | Server command |
| --- | --- | --- |
| 1 | Date and time | `date` |
| 2 | System uptime | `uptime` |
| 3 | Memory usage | `free` |
| 4 | Network connections and statistics | `netstat` |
| 5 | Logged-in users | `who` |
| 6 | Running processes | `ps -ef` |
| 7 | Exit the client | No server command sent |

## Project Files

| File | Responsibility |
| --- | --- |
| `src/Client.java` | Interactive menu, IPv4 address validation, and request selection |
| `src/ClientCommand.java` | Command definitions, client request threads, socket communication, and timing calculations |
| `src/Server.java` | Accepts connections, runs system commands with `ProcessBuilder`, and returns output |

## Requirements

- Java Development Kit (JDK) 8 or later, with `javac` and `java` available.
- A Linux server with `date`, `uptime`, `free`, `netstat`, `who`, and `ps` available on its executable search path. On some Linux distributions, `netstat` requires the `net-tools` package.
- Network connectivity between the client and server, with the selected TCP port permitted by the server's firewall.

No external Java libraries or build tools are required. The client can run on another operating system with Java installed; the system commands execute on the server.

## Compile and Run

From the repository root, compile the three source files:

```bash
mkdir -p out
javac -d out src/Client.java src/ClientCommand.java src/Server.java
```

If the files are stored directly in the repository root, omit `src/` from the source paths.

### Start the server

In one terminal, run:

```bash
java -cp out Server
```

Enter a port number when prompted, for example `2525`. Leave this terminal running.

### Start the client

In another terminal, run:

```bash
java -cp out Client
```

Enter:

- **IP address:** `127.0.0.1` for testing on the same computer, or the server's reachable IPv4 address for a separate computer.
- **Port:** the same port entered on the server, such as `2525`.
- **Command:** a menu option from `1` through `6`.
- **Request count:** `1`, `5`, `10`, `15`, `20`, or `25`.

Repeat with different request counts to compare timing results. Select `7` to exit the client. Stop the server with `Ctrl+C` in its terminal.

## Timing Results

For each request batch, the client reports:

- Each thread's turnaround time in milliseconds.
- The number of requests initiated.
- **Total Elapsed Time:** the sum of all individual request times.
- **Average Time:** the sum of individual request times divided by the number of requests.

Timing begins after the socket connects, immediately before sending the request, and ends after the client finishes reading the response. Connection-establishment time is excluded. The displayed total is a sum of request durations, rather than the batch's wall-clock duration. Failed requests currently retain a zero timing value and remain included in the average.

## Current Limitations

This is an educational implementation intended for a trusted lab environment. Connections are unencrypted and do not require authentication, and responses can reveal server system information.

- The server processes connections sequentially.
- Command errors from standard error are not returned to the client.
- Malformed requests or command-launch failures can terminate the server.
- Numeric input validation, connection timeouts, and client resource cleanup are limited.
- Responses from concurrent client threads may appear interleaved in the terminal.

## Concepts Demonstrated

- TCP communication using `Socket` and `ServerSocket`
- Line-based request and response handling
- Iterative server architecture
- Client threads and coordination with `Thread.join()`
- Operating-system command execution with `ProcessBuilder`
- Response-time measurement using `System.nanoTime()`
- IPv4 input validation using a regular expression
