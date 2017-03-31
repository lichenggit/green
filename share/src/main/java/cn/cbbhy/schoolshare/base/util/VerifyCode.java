package cn.cbbhy.schoolshare.base.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 生成图片验证码
 */
public class VerifyCode {

    private int w = 70;
    private int h = 35;
    private Random r = new Random();
    //以下是一个数组，保存字体的
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312", ""};
    //一下保存可选字符
    private String codes = "23456789abcdefghijkmnopqrstuv" +
            "wxyzABCDEFGHJKMNPQRSTUVWXYZ";
    //背景色 Color(255,255,255)是白色
    private Color bgColor = new Color(255, 255, 255);
    //验证码上的文本
    private String text;

    private BufferedImage createImage() {
        //新建了一个图片缓冲区
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics(); //得到一直画笔
        g2.setColor(this.bgColor);  //因为下面要填充，所以下面是相当于背景颜色
        g2.fillRect(0, 0, w, h);

        return image;
    }

    //随机生成一个字符
    private char randomChar() {
        int index = r.nextInt(codes.length());   //随机获取一个下表
        return codes.charAt(index);
    }

    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];   //随机生成字体名称
        int style = r.nextInt(4);  //生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
        int size = r.nextInt(5) + 24; // 生成随机字号, 24 ~ 28

        return new Font(fontName, style, size);
    }

    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);

    }

    private void drawLine(BufferedImage image) {
        int num = 3; //一共画3条
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) { //一条线两个点，一个点有一个横坐标，一个纵坐标
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h);
            g2.setStroke(new BasicStroke(1.5F));//???????????
            g2.setColor(Color.BLUE);
            g2.drawLine(x1, y1, x2, y2); //画线
        }

    }

    //BufferedImage是图像数据缓冲区
    // 调用这个方法得到验证码
    public BufferedImage getImage() {  //得到一张有验证码的图片
        BufferedImage image = createImage();    //创建了一张画纸（有背景颜色）
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder(); //sb用来装载生成的验证码文本
        //向图片中画4个字符
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + ""; //随机生成一个字符
            sb.append(s);  //用StringBuider方便点
            float x = i * 1.0F * w / 4; //设置当前字符的x轴坐标
            g2.setFont(randomFont());   //设置随机字体
            g2.setColor(randomColor());  //设置随机颜色
            //所有设定后就是画图啦
            g2.drawString(s, x, h - 5);  //一个一个字符来画
        }
        this.text = sb.toString();  //因为text是String，要保存
        //drawLine(image); //在这张画纸上添加干扰线

        return image;
    }

    //这个方法要在getImage()方法后面调用
    public String getText() {
        return text;
    }

    //保存图片到指定的输出流中
    public static void output(BufferedImage image, OutputStream out)
            throws IOException {
        ImageIO.write(image, "JPEG", out);
    }


}
