/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aer;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author pedro
 */
public class AER {
// ola
    /**
     * @param args the command line arguments
     */
    public static void main(String argv[]) throws Exception
      {
         //ActiveRequest table_active = new ActiveRequest();
         RoutingTable table_route = new RoutingTable();
          
          
 	 //SchedulerTCP serverTCP=new SchedulerTCP();
         EmitterUDP EmiUDP=new EmitterUDP();
         ListenerUDP LisUDP=new ListenerUDP();
         //Thread listenerTCP=new Thread(serverTCP);
         Thread treadUDP=new Thread(EmiUDP);
         Thread trightUDP=new Thread(LisUDP);
         
         
         trightUDP.start();
         treadUDP.start();
         
         System.out.println("Hello Listener");
         
         trightUDP.join();
         treadUDP.join();
      }
    
}
