package cn.cbbhy.schoolshare.logic.shiro;

import cn.cbbhy.schoolshare.logic.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class PasswordHelper {
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public static void main(String[] args){
        PasswordHelper passwordHelper = new PasswordHelper();
        User user = new User();
        user.setUsername("lhy");
        user.setPassword("123456");
        passwordHelper.encryptPassword(user);
        System.out.println(user.getSalt());
        System.out.println(user.getCredentialsSalt());
        System.out.println(user.getPassword());
    }
    /**
     * 密码加盐加密
     *
     * @param user
     */
    public void encryptPassword(User user) {
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String storePassword = new SimpleHash(algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();
        user.setPassword(storePassword);
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }
}
