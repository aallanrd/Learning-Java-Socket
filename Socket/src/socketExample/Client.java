/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Client extends JFrame{
    
    private JTextField userTextField;
    private JTextArea chatWindow;
    private ObjectOutput output;
    private ObjectInputStream input;
    private String message = "";
    private String serverIP = "";
    private Socket connection;
    
    public Client(String host){
        super ("Client Data");
        serverIP = host;
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
        
        Client charlie;
        charlie = new Client("127.0.0.1");
        charlie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        charlie.startRunning();
        
    } 

    private void startRunning(){
    try{
        connectToServer();
        setUpStreams();
        whileChatting();
    }   catch(Exception e){
        showMessage("\nClient Terminated Connection\n");
    }finally{
        closeCrap();
    } 
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

    private void showMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                chatWindow.append(message);
            }
        });

    }

    private void closeCrap() {

        showMessage("\nClosing connections\n");
        ableToType(false);
        try {
            output.close();
            input.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connectToServer() throws IOException{
        showMessage("\nAttempting to connect");
        connection =  new Socket(InetAddress.getByName(serverIP),6789);
        showMessage("\nConnected to:" + connection.getInetAddress().getHostName());
        
    
    }

    private void setUpStreams() throws IOException{
        
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input =  new ObjectInputStream(connection.getInputStream());
        
        showMessage( "\nStreams are now Set Up\n");
     
    
    }

    private void whileChatting() {
       //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      ableToType(true);
      
       do{
         //have an interaction    
           try{
               message = (String ) input.readObject();
               showMessage( message);
           }catch(Exception e ){
               showMessage("\nDont Know What I Send\n");
           }
       }
       while(!message.equals("SERVER - END"));
       
    }
    
    private void ableToType(final boolean able) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

              userTextField.setEditable(able);
            }
        });
     
        
    }
    
    
    

  
}
