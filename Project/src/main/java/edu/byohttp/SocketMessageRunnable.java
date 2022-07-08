package edu.byohttp;

import edu.byohttp.controller.Controller;
import edu.byohttp.log.StdoutLogger;
import edu.byohttp.response.ErrorResponseFactory;
import edu.byohttp.response.Response;
import edu.byohttp.response.ResponseStatus;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.net.Socket;

public class SocketMessageRunnable implements Runnable {

    private final InputStream in;
    private final OutputStream out;
    private final Controller controller;


    public SocketMessageRunnable(Socket socket, Controller controller) throws IOException {
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        this.controller = controller;
    }

    public void run() {
        try {
            /* TODO:
                En este punto tenemos un hilo corriendo, con acceso al InputStream que permite leer los bytes del
                request, y un OutputStream que permite escribir los bytes del response.
                A partir de acá podemos procesar el request para producir el response.
             */
            final Response serverResponse = this.controller.processInputMessage(this.readInputStreamMessage());
            StdoutLogger.logResponse(serverResponse);
            writeOutputStream(serverResponse);



            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            /* TODO:
                Esta es la frontera para nuestro código, pues el hilo corre de manera desprendida del main.
                Acá deberíamos registrar este error inesperado en la bitácora para que no pase desapercibido.
             */

            final Response serverResponse = ErrorResponseFactory.generateErrorResponse(ResponseStatus.INTERNAL_SERVER_ERROR);
            writeOutputStream(serverResponse);
            System.out.println(serverResponse);

            }
    }

    private String readInputStreamMessage() {
        try {

            final InputStreamReader inputStreamReader = new InputStreamReader(this.in);
            final BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder message = new StringBuilder();

            String actualLine;

            while ((actualLine = reader.readLine()) != null) {

                if (actualLine.isBlank()) {
                    break;
                }

                message.append(actualLine);
                message.append("\n");
            }
            return message.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeOutputStream(Response response) {

        try {
            final String responseMessage = response.toString();

            final byte[] responseMessageBytes = responseMessage.getBytes();
            final byte[] resourceBytes = response.getResourceBytes().readAllBytes();
            this.out.write(responseMessageBytes);
            this.out.write(resourceBytes);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

}
