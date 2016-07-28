#!/bin/bash
# /etc/init.d/zookeeperclient

case "$1" in
    start)
        echo "Starting Zookeeper Client"
        python /home/master/zookeeperclient.py &
        ;;
    stop)
        echo "Stopping Zookeeper Client"
        #killall zookeeperclient.py
        kill $(ps aux | grep -m 1 'python /home/master/zookeeperclient.py' | awk '{ print $2 }')
        ;;
    *)
        echo "Usage: service Client start|stop"
        exit 1
        ;;
esac
exit 0
