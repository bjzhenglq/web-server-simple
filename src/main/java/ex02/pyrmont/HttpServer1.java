package ex02.pyrmont;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * you can process servlet resources use http://localhost:8080/servlet/ex02.pyrmont.PrimitiveServlet
 * that will invoke servlet.service method, but you should add .class file to webroot folder first.
 * @author mazhiqiang
 * @date 14-3-11.
 */
public class HttpServer1 {

    public static final String SHUTDOWN = "/shutdown";

    private boolean shutdown = false;

    public void await(){
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!shutdown){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);
                request.parse();
                Response response = new Response(outputStream);
                response.setRequest(request);

                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor1 processor1 = new ServletProcessor1();
                    processor1.process(request, response);
                } else {
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                    staticResourceProcessor.process(request, response);
                }
                shutdown = request.getUri().equals(SHUTDOWN);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        HttpServer1 httpServer1 = new HttpServer1();
        httpServer1.await();
    }

}
