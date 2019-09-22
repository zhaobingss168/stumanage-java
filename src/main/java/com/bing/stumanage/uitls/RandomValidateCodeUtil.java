package com.bing.stumanage.uitls;

import com.bing.stumanage.entity.SessionValidCode;
import com.bing.stumanage.repository.SessionValidCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成验证码，并输出到客户端，保存到数据库中
 **/

public class RandomValidateCodeUtil {

    private SessionValidCodeRepository sessionValidCodeRepository = SpringUtil.getBean(SessionValidCodeRepository.class);

    public static final String RANDOMCODEKEY= "RANDOMVALIDATECODEKEY";//放到session中的key
    private String randString = "0123456789";//随机产生只有数字的字符串 private String
    //private String randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生只有字母的字符串
    //private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生数字与字母组合的字符串
    private int width = 166;// 图片宽
    private int height = 40;// 图片高
    private int lineSize = 40;// 干扰线数量
    private int stringNum = 4;// 随机产生字符数量

    private static final Logger logger = LoggerFactory.getLogger(RandomValidateCodeUtil.class);

    private Random random = new Random();

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 28);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("开始生成:"+sdf.format(new Date()));
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);//图片大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 28));//字体大小
        g.setColor(getRandColor(110, 133));//字体颜色
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        System.out.println("数据库操作开始:"+sdf.format(new Date()));
        //将生成的随机字符串保存到session中
        SessionValidCode svc = sessionValidCodeRepository.findOneBySessionId(request.getSession().getId());
        if(null!=svc){
            svc.setValidCode(randomString);
            sessionValidCodeRepository.save(svc);
        }else{
            SessionValidCode c = new SessionValidCode();
            c.setSessionId(request.getSession().getId());
            c.setValidCode(randomString);
            sessionValidCodeRepository.save(c);
        }
        System.out.println("数据库操作结束:"+sdf.format(new Date()));
        g.dispose();
        try {
            System.out.println("写图片到客户端开始:"+sdf.format(new Date()));
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
            System.out.println("写图片到客户端结束:"+sdf.format(new Date()));
        } catch (Exception e) {
            logger.error("将内存中的图片通过流动形式输出到客户端失败>>>> ", e);
        }
        System.out.println("生成结束"+sdf.format(new Date()));
    }

    /**
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 19+13 * i, 26);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }
}
