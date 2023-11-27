import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor iniciado na porta " + port);

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                Random random = new Random();
                int number = random.nextInt(100) + 1;
                System.out.println("Número gerado: " + number);

                out.println("Conectado ao servidor. Por favor, envie sua primeira tentativa.");

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Tentativa recebida: " + inputLine);
                    int guess;
                    try {
                        guess = Integer.parseInt(inputLine);
                    } catch (NumberFormatException e) {
                        out.println("Por favor, insira um número válido.");
                        continue;
                    }

                    if (guess < number) {
                        out.println("maior");
                    } else if (guess > number) {
                        out.println("menor");
                    } else {
                        out.println("acertou");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro na porta" + port);
                System.out.println(e.getMessage());
            }
        }
    }
}
