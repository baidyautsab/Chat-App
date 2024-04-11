## Chat Application
This is a chat application build using the concept of java socket and multithreading.
`ServerSocket serverSocket;` To accept client side request we create a reference of **ServerSocket** class.
Then in the constructor we create an object and assigned the port number from where client send the request.
`Socket socket;` also create the reference of Socket here client request will be stored.
`socket = serverSocket.accept();` using **accept()** method we accept the request.
```
    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream());
```
Here we create object of **BufferedReader()** to read the client side message and create object of **PrintWriter()** to write message.
```
    public void startReading(){ // accepting data
        // creating a thread object of Runnable interface using lambda expression
        Runnable r1 = ()->{
            while (true){ // for now the loop will be executed for infinite time
                try {
                    String clientMsg = br.readLine();
                    System.out.println("Client: " + clientMsg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(r1).start(); // executing the thread
    }
```
This method read the data by **readLine()** method and printing it in the console.
```
    public void startWriting(){ // sending data
        // creating a thread object of Runnable interface using lambda expression
        Runnable r2 = ()->{
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (true) {
                    String clientMsg = consoleReader.readLine(); // Read from console
                    out.println(clientMsg); // Send the message to server
                    out.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        new Thread(r2).start(); // executing the thread
    }
```
This method sending the message from server side.
Same things is repeating in client side also, but there we don't create ServerSocket object, we create **Socket** object and calling the constructor with **ip** address and **port** number.