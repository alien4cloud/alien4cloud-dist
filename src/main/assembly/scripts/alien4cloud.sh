#!/usr/bin/env bash

if [ ! -z "$JAVA_HOME" ] ; then
    JAVA=$JAVA_HOME/bin/java
else
    JAVA=`which java`
fi

if [ ! -x "$JAVA" ] ; then
  echo Cannot find java. Set JAVA_HOME or add java to path.
  exit 1
fi

if [[ ! `ls alien4cloud-ui-${project.version}.war 2> /dev/null` ]] ; then
  if [[ ! `ls alien4cloud-standalone/alien4cloud-ui-${project.version}.war 2> /dev/null` ]] ; then
    echo Command must be run from the directory where the WAR is installed or its parent.
    exit 4
  fi
  cd alien4cloud-standalone
fi

if [ -z "$JAVA_OPTIONS" ] ; then
    JAVA_OPTIONS="-server -showversion -XX:+AggressiveOpts -Xms512m -Xmx2g -XX:MaxPermSize=512m"
fi

$JAVA $JAVA_OPTIONS \
    -cp config/:alien4cloud-ui-${project.version}.war \
    org.springframework.boot.loader.WarLauncher
    "$@"