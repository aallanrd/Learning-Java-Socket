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

import Controller.ClientClass;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Allan
 */
public class ClientMiddle {
    
    
    ClientClass client;
    
    public ClientMiddle (){
        client = new ClientClass();
    }
   
    public void clientFrame_MTest(
            JTextField txtIP,
            JTextField txtPort,
            JLabel label_connect,
            JButton buttonTest
    ) {
        //Get IP From Text
        String serverIP = txtIP.getText();
        
        //Get Port Number From Text
        int portNumber = Integer.parseInt(txtPort.getText());
        
        //Check Connection
        boolean TestConnection = client.TestConnection(serverIP, portNumber);
        
        //If conncetion is Succesfull
        if (TestConnection == true) {
            label_connect.setText("Connection Succesfull! Connected to" + serverIP);
            buttonTest.setEnabled(false);
            
        } 
        //If can't connect to Server!
        else {
            label_connect.setText("Cant connect : Error A0001 : Can't Connect To Server");

        }

    }
    //====================================================================================
    public void clientFrame_MSendM (JTextField txtMessage, JLabel label_connect){
        
        boolean SendMessageToServer = client.SendMessageToServer(txtMessage.getText());
        
        if(SendMessageToServer){
           label_connect.setText("Message Send Succesfull"); 
        }
        else{
           label_connect.setText("Cant connect : Error A0002 : Can't Send Message To Server");
 
        }
    }
    
    //======================================================================================
    public void clientFrame_MCloseC (JLabel label_connect, JButton buttonTest){
        boolean CloseServerConnect = client.CloseServerConnect();
        if(CloseServerConnect){
            label_connect.setText("Connection Close Succesfull"); 
            buttonTest.setEnabled(true);
        }
        else{
            label_connect.setText("Cant connect : Error A0003 : Can't Close Connection to Server");
            
        }
    }
}
