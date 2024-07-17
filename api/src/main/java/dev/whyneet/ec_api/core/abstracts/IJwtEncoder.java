package dev.whyneet.ec_api.core.abstracts;

import java.security.Key;
import java.util.Map;

public interface IJwtEncoder {
    String encodeToken(Map<String, Object> claims, String id, String subject, long maxAge, Key key);
}
