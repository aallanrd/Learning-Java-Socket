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
