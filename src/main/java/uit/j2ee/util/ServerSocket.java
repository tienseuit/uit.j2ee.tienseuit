/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.util;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author LAP10599-local
 */
@ApplicationScoped
@ServerEndpoint("/actions")
public class ServerSocket {

    @OnOpen
    public void open(Session session) {
        System.out.println("open"); 
        session.getAsyncRemote().sendText("open fucking");
    }

    @OnClose
    public void close(Session session) {
        
        System.out.println("close");
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("onError: " + error.getMessage());
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("handleMessage: message: " + message);
        session.getAsyncRemote().sendText("fuckkkkkkkkkkk");
    }
}
