# Agora泛娱乐FullDemo Common Service & KTV Room Service

## 项目配置

请参考`resources/application.properties`，另外开发环境和线上部署环境的差异化配置请参考`resources/application-dev.properties`和`resources/application-prod.properties`

## 编译与运行方法

1. 创建MySQL数据库，数据库名可自定义，配置到连接中即可
2. 使用doc/sql，初始化数据表
3. 使用data/song_data.sql，导入现在歌曲库（如更新更多内容，可以从声网后台白名单到内容中心，异步同步更多歌曲）
4. 使用IDEA打开本工程
5. 按项目配置需求配置服务
   * 配置阿里云短信模板服务(可以通过阿里云后台短信服务申请模板开启)
     * al.sms.accessKeyId为阿里云access key id
     * al.sms.accessKeySecret为阿里云access key secret
     * al.sms.singName为短信模板名
     * al.sms.temPlateCode为短信模板code
     * al.sms.url为阿里云短信服务URL
   * 配置阿里云OSS环境（可以通过阿里云后台OSS存储服务开启）
     * al.oss.accessKeyId为阿里云access key id
     * al.oss.endpoint为OSS的Endpoint
     * al.oss.accessKeySecret为阿里云access key secret
     * al.oss.bucketName为阿里云OSS bucket名
   * 配置声网RTM（可以通过声网控制台申请获得）
     * rtm.java.appId为声网项目app ID（尽可能与客户端APP ID一致）
     * rtm.java.customerKey为声网Restful API客户key
     * rtm.java.customerSecret为声网Restful API客户secret
     * rtm.java.requestId为声网Restful API Request ID
   * 配置依图审核服务（可以通过依图后台申请获得）
     * yitu.devId为依图dev ID
     * yitu.appId为依图app ID
     * yitu.devKey为依图dev Key
     * callback.seed为依图内容审核callback地址
   * 其他配置
     * 其他服务配置都有默认参数，如果你对Spring配置熟悉，可以按自己服务需求进行调整
6. 在IDEA中，选择当前要编译的环境（dev/prod)
    * 配置MySQL连接信息
      * spring.datasource.url为MySQL数据库连接地址
      * spring.datasource.username为MySQL数据库连接用户名
      * spring.datasource.password为MySQL数据库连接密码
    * 配置Redis连接信息
      * spring.redis.host为redis连接地址
      * spring.redis.port为redis连接端口
      * spring.redis.password为redis连接密码，如果为空则留空即可
    * 其他配置
      * 其他服务配置都有默认参数，如果你对Spring配置熟悉，可以按自己服务需求进行调整
7. 在”研判中心“ -> "生命周期"中，双击package，开始打包，或在命令行中运行`mvn package -P dev`或`mvn packege -P prod`生成`ktvOnline-0.0.1-SNAPSHOT.jar`
8. 将目标jar上传至指定服务器，执行`nohup java -jar ktvOnline-0.0.1-SNAPSHOT.jar &` ，服务即可运行