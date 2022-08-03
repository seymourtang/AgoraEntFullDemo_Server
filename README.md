# Agora泛娱乐FullDemo Common Service & KTV Room Service

## 项目配置

请参考`resources/application.properties`，另外开发环境和线上部署环境的差异化配置请参考`resources/application-dev.properties`和`resources/application-prod.properties`

## 编译与运行方法

1. 创建MySQL数据库，数据库名可自定义，配置到连接中即可
2. 使用doc/sql，初始化数据表
3. 使用data/song_data.sql，导入现在歌曲库（如更新更多内容，可以从声网后台白名单到内容中心，异步同步更多歌曲）
4. 使用IDEA打开本工程
5. 按项目配置需求配置服务
6. 在IDEA中，选择当前要编译的环境（dev/prod)
7. 在”研判中心“ -> "生命周期"中，双击package，开始打包，或在命令行中运行`mvn package -P dev`或`mvn packege -P prod`生成`ktvOnline-0.0.1-SNAPSHOT.jar`
8. 将目标jar上传至指定服务器，执行`nohup java -jar ktvOnline-0.0.1-SNAPSHOT.jar &` ，服务即可运行