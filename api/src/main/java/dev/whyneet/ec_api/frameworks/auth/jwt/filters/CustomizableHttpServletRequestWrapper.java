package dev.whyneet.ec_api.frameworks.auth.jwt.filters;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class CustomizableHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final ArrayList<Cookie> cookies;

    public CustomizableHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.cookies = new ArrayList<>(Arrays.asList(request.getCookies()));
    }

    @Override
    public Cookie[] getCookies() {
        return cookies.toArray(new Cookie[0]);
    }

    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    public void replaceCookie(Cookie cookie) {
        OptionalInt idx = IntStream.range(0, cookies.size()).filter(c -> Objects.equals(cookies.get(c).getName(), cookie.getName())).findFirst();
        if (idx.isEmpty()) addCookie(cookie);
        else cookies.set(idx.getAsInt(), cookie);
    }
}
