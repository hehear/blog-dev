FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ARG DEPENDENCY=MyBlog
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.blog.MyBlogApplication"]