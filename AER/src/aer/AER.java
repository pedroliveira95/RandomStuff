/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aer;


import aer.Data.Node;
import aer.TCP.EmitterTCP;
import aer.TCP.ListenerTCP;
import aer.UDP.Hello;
import aer.UDP.ListenerUDP;
import aer.UDP.UDPQueue;
import aer.miscelaneous.Config;
import aer.miscelaneous.Controller;
import aer.miscelaneous.ZoneWatch;

/**
 *
 * @author pedro
 */
public class AER {

    public static void main(String argv[]) throws Exception
      {
         System.out.println("Init Adhoc Node....");
         
         Config     config  = new Config();
         Controller control = new Controller(config.getQueueSize());
         
         // Node Setup
         Node id = new Node(config);
         
         //WatchDog
        // ZoneWatch  wd_zone     = new ZoneWatch(control, config, id);
         //Thread     t_wd_zone   = new Thread(wd_zone);
         
 	 //UDP Thread Object Init + Thread Init
         //UDPQueue       queueUDP    = new UDPQueue(control);
         //ListenerUDP    listenerUDP = new ListenerUDP(control,id);
         //Hello          helloUDP    = new Hello(control,config,id);
         
        // Thread t_queue_UDP         = new Thread(queueUDP);
        // Thread t_listener_UDP      = new Thread(listenerUDP);
        // Thread t_emitter_UDP       = new Thread(helloUDP);
        
         //TCP Thread Object Init + Thread Init
         ListenerTCP listenerTCP    = new ListenerTCP(control, config, id);
         EmitterTCP emitterTCP      = new EmitterTCP();
         
         Thread t_listener_TCP      = new Thread(listenerTCP);
         Thread t_emitter_TCP       = new Thread(emitterTCP);
         
         // TCP + UDP + WatchDog's Thread Start
        
         //t_queue_UDP.start();
         //t_listener_UDP.start();
         //t_emitter_UDP.start();
         t_listener_TCP.start();
         Thread.sleep(500);
         t_emitter_TCP.start();
         
         
         //t_wd_zone.start();
         
         //Thread.sleep(120000);
         
         
         
         synchronized(control){
             control.setUDPFlag(false);
         }
         synchronized(control){
             control.setWatchDogFlag(false);
         }
         
         // Thread close Wait
         //t_queue_UDP.join();
         //t_listener_UDP.join();
         //t_emitter_UDP.join();
         t_listener_TCP.join();
         t_emitter_TCP.join();
        
         //t_wd_zone.join(); //Add a way to kill Watchdog Thread!!
        
         System.out.println("Close Adhoc Node....");
      }
    
}
