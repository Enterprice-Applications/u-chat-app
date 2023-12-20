package lk.ijse.dep11.wscontroller;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.dep11.to.MessageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class ChatWSController extends TextWebSocketHandler {
    private final List<MessageTO> chatMessage = new Vector<>();

    private final List<WebSocketSession> webSocketSessionList = new ArrayList<>();

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private LocalValidatorFactoryBean validatorFactoryBean;
    public ChatWSController(){

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessionList.add(session);
    } // this works After Handshake connection start time

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try{
            MessageTO messageObj = mapper.readValue(message.getPayload(), MessageTO.class); // Convert to MessageTO @RequestBody
            Set<ConstraintViolation<MessageTO>> violations = validatorFactoryBean.getValidator().validate(messageObj); // Validate the message @Valid
            if(violations.isEmpty()){
                // If message is validate
                // BroadCast message to other clients
                for (WebSocketSession webSocketSession: webSocketSessionList){
                    if (webSocketSession == session) continue;
                    if(webSocketSession.isOpen()){
                        webSocketSession.sendMessage(new TextMessage(message.getPayload()));
                    }
                }
            }else{
                session.sendMessage(new TextMessage("Invalid Message Schema"));
            }
        }catch (JacksonException exp){
            session.sendMessage(new TextMessage("Invalid JSON"));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessionList.remove(session);
    } // this works when off the connection
}
