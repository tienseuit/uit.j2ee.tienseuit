/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.util;
  
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.websocket.Session;
import uit.j2ee.been.Device;

@ApplicationScoped
public class DeviceSessionHandler {
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Device> devices = new HashSet<>();
    
    public List<Device> getDevices() {
        return new ArrayList<>(devices);
    }

    public void addDevice(Device device) {
    }

    public void removeDevice(int id) {
    }

//    public void toggleDevice(int id) {
//    }
//
//    private Device getDeviceById(int id) {
//        return null;
//    }
//
//    private JsonObject createAddMessage(Device device) {
//        return null;
//    }
//
//    private void sendToAllConnectedSessions(JsonObject message) {
//    }
//
//    private void sendToSession(Session session, JsonObject message) {
//    }
}