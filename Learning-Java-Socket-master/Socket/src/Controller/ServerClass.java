/*
 * Copyright (C) 2015 Allan Rojas.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Controller;

import java.awt.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;

/**
 *
 * @author Allan
 */
public class ServerClass {
    
    public ServerSocket serverx;
    public Thread thread;
   
     
    private ObjectOutput output;
    private ObjectInputStream input;
    private Socket connection;
    
   
    //=============================================================   
    public boolean checkSocketServer(int port, int aam ){
         try{
            
            
            serverx = new ServerSocket(port, aam);
            return true;
         
         }
         catch(Exception e){
           return false;
         }
     }
    
    //===========================================================
    public boolean checkOutputRequest() {
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            
            input = new ObjectInputStream(connection.getInputStream());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
  //===============================================================
    
    public void startListeningPort(List list, int port, int aam) {

        thread = new Thread() {
            @Override
            public void run() {
                try {
                    checkSocketServer(port, aam);
                    showMessage("Waiting for connection", list);
                    connection = serverx.accept();
                    while (true) {
                        checkOutputRequest();
                        showMessage("Now Connected!", list);
                        while (true) {
                            String message = (String) input.readObject();
                            showMessage(message, list);
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    showMessage("Disconnected", list);
                    startListeningPort(list, port, aam);
                    thread.start();
                }
            }
        };
    }

    private void showMessage(final String text , List list1){
        SwingUtilities.invokeLater(() -> {
            list1.add(text);
        });
    }
    
    
}
