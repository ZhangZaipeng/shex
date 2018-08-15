#!/bin/bash

APP_PORT=${appserverPort}
APP_NAME=${appName}
MODULE_NAME=${moduleName}

TOMCAT_SERVER_HOME=${deployhome}/tomcat_server
OUTPUT_HOME=${outputroot}/${APP_NAME}
# /home/www/htdocs/shex-api-server/web-deploy
DEPLOY_HOME=${deployhomeroot}/${APP_NAME}/web-deploy

