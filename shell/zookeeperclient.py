#!/usr/local/bin/python3
from kazoo.client import KazooClient
import time

zk = KazooClient(hosts = '192.168.43.125:2181',connection_retry = True)
zk.start()
zk.create("/datasource/datasource1", b"DATASOURCE1", None, True, False, True)
while True:
    try:
        time.sleep(2)
    except (KeyboardInterrupt, SystemExit):
        zk.stop()

