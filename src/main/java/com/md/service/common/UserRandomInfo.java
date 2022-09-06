package com.md.service.common;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UserRandomInfo {

    private static String nikeName = "James,William,Lucas,Henry,Jack,Daniel,Michael,Logan,Owen,Ashley,Aaron,Cooper,Alex,Wesley,Adam,Bryson,Jasper,Jason,Cole,Ace,Ivan,Leon,Brandon,Joe,Jenny,Simon,Kylie,Kobe,Jay,Travis,Jared,Jefferey,Hassan,Dash,Mia,Isabella,Emily,Layla,Nora,Lily,Zoe,Stella,Elena,Claire,Alice,Bella,Cora,Eva,Iris,Maria,Lucia,Jasmine,Olive,Blake,Aspen,Myla,Hanna,Julie,Eve";
    private static String headImage = "https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/man1.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/man2.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/man3.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/man4.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/man5.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/man6.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/man7.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/man8.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/man9.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/woman1.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/woman2.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/woman3.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/woman4.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/woman5.png.https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/woman6.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/woman7.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/woman8.png,https://beidou-ktv-user.oss-cn-beijing.aliyuncs.com/headImage/woman9.png";

    public static String getName() {
        List<String> snList = Arrays.asList(nikeName.split(","));
        Random random = new Random();
        return snList.get(random.nextInt(snList.size() - 1));
    }

    public static String headImage() {
        List<String> snList = Arrays.asList(headImage.split(","));
        Random random = new Random();
        return snList.get(random.nextInt(snList.size() - 1));
    }
}
