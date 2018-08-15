#!/bin/bash

BASE_BIN_DIR=`dirname $0`
# public env
. $BASE_BIN_DIR/base_env.sh

# private env
if [ -f $BASE_BIN_DIR/env.sh ]; then
	. $BASE_BIN_DIR/env.sh
fi

## jetty所需相关参数
TOMCAT_WEBAPPS="$TOMCAT_SERVER_HOME/webapps"
TOMCAT_PID="$OUTPUT_HOME/logs/tomcat.pid"
JAVA="$JAVA_HOME/bin/java"
JAVA_OPTIONS="$JAVA_OPTS" ## jvm参数

export TOMCAT_WEBAPPS TOMCAT_PID TOMCAT_LOGS JAVA_OPTIONS JAVA

prepare() {
	# delete tomcat work home dir, then make the jetty work
	if [ -d "$TOMCAT_SERVER_HOME" ] ; then
   		rm -rf  "$TOMCAT_SERVER_HOME"
	fi

	if [ ! -d "$TOMCAT_SERVER_HOME" ] ; then
    	mkdir -p "$TOMCAT_SERVER_HOME"
	fi
	
	# create dir
	mkdir -p "$OUTPUT_HOME/logs"
  	mkdir -p "$TOMCAT_WEBAPPS"
	if [ ! -f "$OUTPUT_HOME/logs/${MODULE_NAME}_stdout.log" ] ; then
  		touch "$OUTPUT_HOME/logs/${MODULE_NAME}_stdout.log"
  	fi
  	  	
	# rm -rf  "$JETTY_WEBAPPS/root.war $JETTY_WEBAPPS/root/"
  	# cp $DEPLOY_HOME/*.war  $JETTY_WEBAPPS/root.war
  	rm -rf  "$TOMCAT_WEBAPPS/*.jar"
  	mv $DEPLOY_HOME/*.jar  $TOMCAT_WEBAPPS/$MODULE_NAME.jar
  	echo -e "has copyed $MODULE_NAME.jar"
}


start() {
	prepare
	## 启动tomcat
	$JAVA $JAVA_OPTIONS -jar $TOMCAT_WEBAPPS/$MODULE_NAME.jar
}


stop() {
    BOOT_SHUTDOWN_URL="http://127.0.0.1:${APP_PORT}/${MODULE_NAME}/shutdown"
	curl -X POST $BOOT_SHUTDOWN_URL
}

if [ "$1" == "-v" ] || [ "$1" == "-h" ]; then
  echo "Useage: tomcatctl.sh run   ##前台启动"
  echo "        tomcatctl.sh start ##后台异步 tomcat --daemon"
  echo "        tomcatctl.sh stop  ##关闭"
elif [ "$1" == "start" ]; then
  start
elif [ "$1" == "stop" ]; then
  stop
else
  run
fi
