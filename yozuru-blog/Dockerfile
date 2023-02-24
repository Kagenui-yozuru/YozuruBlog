#基础镜像使用jdk1.8
FROM java:8
#作者
MAINTAINER yozuru
# VOLUME 指定临时文件目录为/tmp，在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名
ADD forestage-app.jar forestage-app.jar
# 运行jar包
RUN bash -c 'touch /forestage-app.jar'
ENTRYPOINT ["java","-jar","/forestage-app.jar"]
#暴露7777端口作为微服务
EXPOSE 7777