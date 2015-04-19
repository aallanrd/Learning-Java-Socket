/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.List;
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
    
    public void startListeningPort(List list,int port, int aam){
        
        thread = new Thread() {
            @Override
            public void run() {
               
                try {
                    checkSocketServer(port,aam);
                    showMessage("Waiting for connection",list);
                    connection = serverx.accept();
                  // Sho("Waiting" + connection.getInetAddress().getHostName());

                    boolean checkOR = false;
                    while (true) {
                        checkOR = checkOutputRequest();
                      
                        showMessage("Now Connected!",list);
                       
                        String message = null;
                        while (true) {
                            message = (String) input.readObject();
                            showMessage(message,list);
                        }
                    }

                    
                } catch (Exception ex) {
                    showMessage("Disconnect" ,list);  
                    run();
                }
            }
        };
    }
    
    private void showMessage(final String text , List list1){
        SwingUtilities.invokeLater( new Runnable() {

            @Override
            public void run() {

            list1.add(text);
            }
        });
    }
    
    
}
