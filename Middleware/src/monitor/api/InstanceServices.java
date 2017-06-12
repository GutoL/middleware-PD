/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import monitor.MachineInformation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gprt
 */
public class InstanceServices {
    private String token;
    //mudar caso o nome seja diferente da rede
    private final static String NETWORK="intranet";
    
    public void setToken(String token){
        this.token=token;
    }
    
    public ArrayList<MachineInformation> getServers(){
        Requests requests=new Requests();
        ArrayList<MachineInformation> serversInformation=new ArrayList<>();
        HttpResponse<JsonNode> serversRequest=requests.requestServers(token);
         try {
            JSONArray jsonArray= serversRequest.getBody().getObject().getJSONArray("servers");
            for(int i=0;i<jsonArray.length();i++){
                String id=jsonArray.getJSONObject(i).getString("id");
                serversInformation.add(getServer(id));
            }    
            
        } catch (JSONException ex) {
            Logger.getLogger(InstanceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serversInformation;
    }
    
    public MachineInformation getServer(String id){
        Requests requests=new Requests();
        HttpResponse<JsonNode> serversRequest=requests.requestServer(token,id);
        MachineInformation machine=new MachineInformation();
        try {
            String ip=serversRequest.getBody().getObject().getJSONObject("server").getJSONObject("addresses").getJSONArray(NETWORK).getJSONObject(0).getString("addr");
            String name=serversRequest.getBody().getObject().getJSONObject("server").getString("name");
            machine.setIP(ip);
            machine.setName(name);
            machine.setPort(1010);
             
        } catch (JSONException ex) {
            Logger.getLogger(InstanceServices.class.getName()).log(Level.SEVERE, null, ex);
            machine=null;
        }
        
        //machine.setIP();
        return machine;
       
    }
    
}