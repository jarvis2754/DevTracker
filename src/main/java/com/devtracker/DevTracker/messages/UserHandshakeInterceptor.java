package com.devtracker.DevTracker.messages;

import com.devtracker.DevTracker.config.JwtUtil;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class UserHandshakeInterceptor implements HandshakeInterceptor {
    private final JwtUtil jwtUtil;

    public UserHandshakeInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // 1) Try Authorization header: "Authorization: Bearer <token>"
        List<String> authHeaders = request.getHeaders().get("Authorization");
        if (authHeaders != null && !authHeaders.isEmpty()) {
            String bearer = authHeaders.get(0);
            if (bearer != null && bearer.startsWith("Bearer ")) {
                String token = bearer.substring(7);
                // Adapt the method names below to your JwtUtil implementation:
                if (jwtUtil.validateToken(token)) {
                    Integer userId = jwtUtil.extractUserId(token); // e.g. string user id
                    if (userId != null) {
                        attributes.put("userId", userId);
                        return true;
                    }
                }
                // invalid token -> reject handshake
                return false;
            }
        }

        // 2) Fallback: parse query params (e.g. token= or userId=). Prefer token if present.
        String query = request.getURI().getQuery();
        if (query != null && !query.isEmpty()) {
            Map<String, String> params = Arrays.stream(query.split("&"))
                    .map(s -> s.split("=", 2))
                    .filter(arr -> arr.length == 2)
                    .collect(Collectors.toMap(
                            arr -> URLDecoder.decode(arr[0], StandardCharsets.UTF_8),
                            arr -> URLDecoder.decode(arr[1], StandardCharsets.UTF_8),
                            (a, b) -> a // keep first if duplicated
                    ));

            // If a token param is passed, validate it.
            if (params.containsKey("token")) {
                String token = params.get("token");
                if (jwtUtil.validateToken(token)) {
                    Integer userId = jwtUtil.extractUserId(token);
                    if (userId != null) {
                        attributes.put("userId", userId);
                        return true;
                    }
                }
                return false;
            }

            // Last-resort: accept a userId query param (UNVERIFIED). Prefer verified token above.
            if (params.containsKey("userId")) {
                attributes.put("userId", params.get("userId"));
                return true;
            }
        }

        // reject if nothing valid found
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // no-op
    }
}
