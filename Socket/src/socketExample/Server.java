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
package socketExample;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author Allan
 */
public class Server extends JFrame{
    
    private JTextField userTextField;
    private JTextArea chatWindow;
    private ObjectOutput output;
    private ObjectInputStream input;
    
    private ServerSocket serverx;
    private Socket connection;
    
    public Server(){
        super ("Allans Test Server");
        userTextField = new JTextField();
        userTextField.setEditable(false);

        userTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             sendMessage(ae.getActionCommand());
             userTextField.setText("");
            }
        });
        add(userTextField, BorderLayout.NORTH);
        chatWindow =  new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(300, 150);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args){
        
        Server sally = new Server();
        sally.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sally.startRunning();
        
        
    }   
    
    public void startRunning(){
        try{
            
            //Arg 0: Puerto donde vamos alojar el server
            //Arg 1: Cantidad de request al server
            serverx = new ServerSocket(6789, 100);
            while(true){
                try{
                    waitForConnection();
                    setUpStreams();
                    whileChatting();
                    
                }
                catch(Exception g){
                    showMessage("Server Ended the Connection!");
                }finally{
                    closerCrap();
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void closerCrap() {
    
        showMessage( "Closing connections");
        ableToType(false);
        try{
            output.close();
            input.close();
            connection.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }

    private void waitForConnection() throws IOException{
    
        showMessage("Waiting for someone to connect...");
         connection = serverx.accept();
         showMessage("Now Connected To: " + connection.getInetAddress().getHostName());
         
    }
    private void setUpStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input =  new ObjectInputStream(connection.getInputStream());
        
        showMessage( "\nStreams are now Set Up\n");
        
    }

    private void whileChatting() {
      
       String message = "\nYou are now connected";
       sendMessage(message);
       ableToType(true);
       
       do{
         //have an interaction    
           try{
               message = (String ) input.readObject();
               showMessage( message);
           }catch(Exception e ){
              // showMessage("\nDont Know What I Send");
               message = "CLIENT - END";
               closerCrap();
           }
       }
       while(!message.equals("CLIENT - END"));
       
    }

    private void sendMessage(String message) {
     
        try{
            
            output.writeObject("SERVER - "+ message);
            output.flush();
            showMessage("\nSERVER -" + message);
            
        }catch(Exception e){
            chatWindow.append("Error : Cant Send Message");
        }
    }

    private void ableToType(final boolean able) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

              userTextField.setEditable(able);
            }
        });
     
        
    }
   
    private void showMessage(final String text){
        SwingUtilities.invokeLater( new Runnable() {

            @Override
            public void run() {

            chatWindow.append(text);
            }
        });
    }
    
    
}






