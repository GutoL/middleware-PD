/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.util.logging.Level;
import java.util.logging.Logger;
import middleware.client.ClientRequestHandler;
import middleware.util.Marshaller;

/**
 *
 * @author guto
 */
public class MonitorAgent extends Thread{
 
    MonitorInterface monitor;
    float time;// time in seconds
    String nameMachine;
    ClientRequestHandler crh;
        

    public MonitorAgent() {
    }

    public MonitorAgent(String name){
        this.nameMachine = name;
    }
    
    public MonitorAgent(MonitorInterface monitor, float time, String name) {
        this.monitor = monitor;
        this.time = time;
        this.nameMachine = name;
        
    }
    
    
    public void run(){
        
        
        
        while (true) {            
        
            //System.out.println("Send status to controller...");
            sendStatusMachine(this.monitor.getStatusMachine());
            //System.out.println("Sended status to controller...");
            
            try {
                
                Thread.sleep((long) (this.time*1000));
            
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
        
        
    }
    public void sendStatusMachine(StatusMachine sm){
        //demis:10.0.0.75
        crh = new ClientRequestHandler("10.0.0.75", 2425); // IP and port of  MonitorController
        Marshaller marshaller = new Marshaller();
        byte [] statusMsg;
        
        try {
        
            sm.nameMachine = this.nameMachine;
            System.out.println(sm.CPUConsumption);
            statusMsg  = marshaller.marshall(sm);
            //System.out.println("IP: "+crh.getHost()+" porta: "+crh.getPort());
            crh.send(statusMsg, true);
            System.out.println("Sended");
            //Thread.sleep(5000);// send every 5 seconds
            
        } catch (Exception e) {
          
            e.printStackTrace();
            
        }
        
        
    }
    
    
}
