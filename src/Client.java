import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int port = 1234;

        System.out.println("Tentando conectar ao servidor " + hostName + " na porta " + port);
        try (Socket socket = new Socket(hostName, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String fromServer;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Servidor: " + fromServer);
                if ("acertou".equalsIgnoreCase(fromServer)) {
                    System.out.println("Você acertou o número!");
                    break;
                }

                System.out.print("Sua tentativa: ");
                String fromUser = stdIn.readLine();
                if (fromUser != null) {
                    out.println(fromUser);
                    out.flush();
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + hostName);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Não foi possível obter I/O para a conexão com " + hostName);
            e.printStackTrace();
        } finally {
            System.out.println("Conexão com o servidor encerrada.");
        }
        // O socket será fechado automaticamente aqui por causa do try-with-resources
    }
}
