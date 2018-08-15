#!/bin/bash

APP_PORT=${appserverPort}
APP_NAME=${appName}
MODULE_NAME=${moduleName}

TOMCAT_SERVER_HOME=${deployhomeroot}/${appName}/web-deploy/tomcat_server
OUTPUT_HOME=${outputroot}/${appName}
# /home/www/htdocs/shex-api-server/web-deploy
DEPLOY_HOME=${deployhomeroot}/${appName}/web-deploy

