#!/bin/bash

APP_PORT=${appserverPort}
APP_NAME=${appName}
MODULE_NAME=${moduleName}

export DEPLOY_HOME_ROOT=${deployhomeroot}
export OUT_PUT_ROOT=${outputroot}

TOMCAT_SERVER_HOME=$DEPLOY_HOME_ROOT/$APP_NAME/web-deploy/tomcat_server
OUTPUT_HOME=$OUT_PUT_ROOT/$APP_NAME
DEPLOY_HOME=$DEPLOY_HOME_ROOT/$APP_NAME/web-deploy

