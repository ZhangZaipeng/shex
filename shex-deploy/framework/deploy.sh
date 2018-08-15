#!/bin/bash
set -x
set -e

exec 8<>/home/www/sbin/jm_web.lock
flock -n 8 ||exit 1

SourcePath=/home/www/project/shex
appName=${appName}
ProjectPackgePath=/home/www/project/shex/shex-deploy/target/web-deploy.zip
Devn=dev
LockFile=~/sbin/$appName.lock

###项目打包编译
cd $SourcePath
git pull
mvn clean package -Denv=$Devn

### 运行 脚本
/bin/bash /home/www/sbin/ProjectRestart.sh