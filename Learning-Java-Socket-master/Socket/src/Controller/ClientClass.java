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

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author Allan
 */
public class ClientClass {
    
    private ObjectOutput output;
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
            
             
             
          //  output.writeObject(new Model.ModelTemperatura("18,7", new Date("26/02/1991")));
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
