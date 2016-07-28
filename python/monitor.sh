#!/bin/bash
# /etc/init.d/monitor.sh
#

case "$1" in
    start)
        echo "开启监控服务"
        python3 /Users/codeai/Desktop/monitor.py &
        # 等待1秒 查看是否有监控服务
        # 如果没有 启动失败
        # 如果有 再启动 client 服务
        sleep 2
        if [ "$(ps -ef | grep zookeeperclient.py | grep -v grep | awk '{ print $2 }')" = ""]
        then
            echo "监控服务开启失败"
            exit 1
        else
            echo "开启 client 服务"
            python3 /Users/codeai/Desktop/zookeeperclient.py &
        fi
        ;;
    stop)
        echo "关闭client服务"
        kill $(ps -ef | grep zookeeperclient.py | grep -v grep | awk '{ print $2 }')
        sleep 2
        echo "关闭监控服务"
        kill $(ps -ef | grep monitor.py | grep -v grep | awk '{ print $2 }')
        ;;
    *)
        echo "Usage: service command start|stop"
        exit 1
        ;;
esac
exit 0
