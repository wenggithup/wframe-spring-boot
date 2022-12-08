FROM azul/zulu-openjdk-alpine:8-jre

MAINTAINER wengdaxia

ARG workdir="/data/linkdood"

RUN mkdir -p ${workdir}/im/javaserver/quickStart && mkdir -p /data/linkdood/logs

WORKDIR ${workdir}/im/javaserver/quickStart


# Env作用域为全局，可在docker run -e进行参数覆盖
ENV jarName="app.jar"

ARG sourceJarName="wframe-boot-quickstart-1.0-SNAPSHOT.jar"

# 拷贝镜像
ADD ./target/$sourceJarName ${workdir}/im/javaserver/quickStart/$jarName

# 功能为设置环境变量
# 普通参数，如指定启动环境等
ENV PARAMS=""
# JVM参数
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java -jar $JAVA_OPTS $jarName $PARAMS"]