#!/bin/bash
set -x
if [ ! -d /home/www/htdocs/$appName ];then
	mkdir -p /home/www/htdocs/$appName
fi

time=`date '+%Y-%m-%d-%H:%M'`

cp /home/www/htdocs/$appName/web-deploy.zip /home/www/htdocs/$appName/web-deploy$time.zip
/home/www/htdocs/$appName/web-deploy/bin/killws.sh
rm -rf /home/www/htdocs/$appName/web-deploy

cd /home/www/htdocs/$appName;unzip -d web-deploy web-deploy.zip
/home/www/htdocs/$appName/web-deploy/bin/startws.sh