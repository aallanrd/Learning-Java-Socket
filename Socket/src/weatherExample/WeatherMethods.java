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

package weatherExample;

import it.octograve.weatherlib.Station;
import it.octograve.weatherlib.StationsList;
import it.octograve.weatherlib.WeatherException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Allan
 */
public class WeatherMethods {
    
    Wait waitW;
    String location;
    
    Thread thread2;
    Thread thread;
    
    JTextArea textArea;
    //==========================================

    public void createThreads(JFrame frame,JTextArea textA, String location) {
        this.textArea = textA;
        this.location = location;
        //========================================
        thread = new Thread() {
            @Override
            public void run() {
                waitW = new Wait(frame, true);
                waitW.setVisible(true);
            }
        };
   //===========================================

        thread2 = new Thread() {
            @Override
            public void run() {

                try {
                    fetchStation();
                    waitW.setVisible(false);
                    thread.stop();
                    thread2.stop();
                } catch (WeatherException ex) {
                    Logger.getLogger(TestingWeather.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

   //=======================================
    private void fetchStation() throws WeatherException {

        StationsList list = null;
        try {
            list = StationsList.fetchStationsList();
        } catch (WeatherException e) {
            textArea.append(
                    "\nUnable to fetch stations list."
            );

        }
        //====================================================
        Station station = list.getByLocation(location);
        if (station == null) {
            textArea.append("\n'" + location
                    + "' is not a valid location");

        } //=======================================================
        else {
            station.updateWeather();
            textArea.append("\nWeather informations for " + station.getLocation());
            textArea.append("\n Fecha :" + station.getWeather().toVerboseString());
        }
    }

    
    
}
