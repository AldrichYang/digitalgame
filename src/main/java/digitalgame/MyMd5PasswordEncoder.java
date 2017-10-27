package digitalgame;

import digitalgame.common.Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by yh on 17/10/27.
 */
public class MyMd5PasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(CharSequence charSequence) {
        return Util.getMD5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(encode(charSequence));
    }
}
