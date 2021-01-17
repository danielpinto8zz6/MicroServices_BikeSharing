package estg.mtsd.bikeshare.shared.library.utils;

import java.text.ParseException;

import com.nimbusds.jose.Header;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

public class JwtUtils {
    public static String parseUserEmail(String token) {
        token = token.replace("Bearer ","");

        JWT jwt;
        try {
            jwt = JWTParser.parse(token);

            Header header = jwt.getHeader();

            JWTClaimsSet jwtClaimSet = jwt.getJWTClaimsSet();

            String email = jwtClaimSet.getSubject();

            return email;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
