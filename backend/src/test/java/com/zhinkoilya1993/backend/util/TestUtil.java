package com.zhinkoilya1993.backend.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class TestUtil {

    private TestUtil() {
    }

    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action.andReturn()), clazz);
    }

    public static <T> T readFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(result), clazz);
    }

    public static <T> List<T> readListFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValues(getContent(result), clazz);
    }

    public static boolean validateJwtToken(String authToken, String jwtSecret) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        } catch (SignatureException e) {
            return false;
        }
        return true;
    }

}
