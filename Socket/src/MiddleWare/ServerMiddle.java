/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiddleWare;

import Controller.ServerClass;
import java.awt.List;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Allan
 */
public class ServerMiddle {
    
    
    ServerClass server;
    
    
    public ServerMiddle(){
        server = new ServerClass();
    }
    //======================================================================
    public void serverFrame_MCheckSocket(JTextField txtPort, JTextField txtAAM, JLabel label_connect){
        
        int port = Integer.parseInt(txtPort.getText());
        int aam = Integer.parseInt(txtAAM.getText());
        boolean checkSocketServer = server.checkSocketServer(port, aam);
        if(checkSocketServer){
             label_connect.setText("Server Socket Created" );
        }else{
             label_connect.setText("Cant Create Socket! Warning" );
        }
        
    }
   //=======================================================================
    public boolean serverFrame_MCheckStreams(){
        
        return  server.checkOutputRequest();
        
    }
  //==========================================================================
    public void createXAjax(List list,JTextField txtPort, JTextField txtAAM, JLabel label_connect )
    {
        int port = Integer.parseInt(txtPort.getText());
        int aam = Integer.parseInt(txtAAM.getText());
        server.startListeningPort(list, port, aam);
        server.thread.start();
    }    
}
