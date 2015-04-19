/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Label;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Allan
 */
public class ClientClass {
    
    private ObjectOutput output;
    private ObjectInputStream input;
    private String message = "";
    private int portNumber = 6789;
    private String serverIP = "127.0.0.1";
    private Socket connection;
    
    
    
    
    //String serverIP , String portNumber
    public boolean TestConnection(String serverIP, int portNumber){
        try {
            
            connection = new Socket(InetAddress.getByName(serverIP), portNumber);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            return true;

        } catch (NumberFormatException | IOException ex) {  
            return false;
        }
    }
   //Public void sendMessage to Server
    public boolean SendMessageToServer(String message){
         try{
            
            output.writeObject(message);
            output.flush();
            return true;
            
        }catch(Exception e){
            return false;
        }
    }
   //Close Connection To Server 
    
    public boolean CloseServerConnect()
    {
      try {
            
            output.close();
            connection.close();
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
}
