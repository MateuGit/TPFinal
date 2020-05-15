package com.utn.UTNphones.Sessions;

import com.utn.UTNphones.Models.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;


@Component
public class SessionManager {
    Map<String, Session> sessionMap = new Hashtable<>();

    int sesionExpiration = 10000;

    public String createSession(User user) {
        String token = UUID.randomUUID().toString();
        sessionMap.put(token, new Session(token, user, new Date(System.currentTimeMillis())));
        return token;
    }

    public Session getSession(String token) {
        if (StringUtils.isEmpty(sessionMap.get(token)))return null;
        Session session = sessionMap.get(token);
        if (session != null) {
            session.setLastAction(new Date(System.currentTimeMillis()));
        }
        return session;
    }

    public void removeSession(String token) {
        sessionMap.remove(token);
    }

    public void expireSessions() {
        for (String k : sessionMap.keySet()) {
            Session v = sessionMap.get(k);
            if (v.getLastAction().getTime() < System.currentTimeMillis() + (sesionExpiration * 1000)) {
                System.out.println("Expiring session " + k);
                sessionMap.remove(k);
            }
        }
    }

    public Optional<User> getCurrentUser(String token) {
      return Optional.ofNullable(getSession(token)).map(Session::getLoggedUser);

    }
}
