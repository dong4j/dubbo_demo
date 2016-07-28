# 监控端口
import socket
import time
import psutil
import smtplib
from email.mime.text import MIMEText
from email.header import Header

mail_host = "smtp.163.com"
mail_user = ""
mail_pass = ""
mail_postfix = "163.com"
log_dir = "sendmail_err_log.log"

def send_mail(to_list, title, context):
    me = mail_user + "<" + mail_user + "@" + mail_postfix + ">"
    msg = MIMEText(context, 'plain', 'utf-8')
    msg['Subject'] = Header(title, 'utf-8')
    msg['From'] = me
    msg['To'] = ";".join(to_list)
    try:
        s = smtplib.SMTP()
        s.connect(mail_host)
        s.login(mail_user, mail_pass)
        s.sendmail(me, to_list, msg.as_string())
        s.quit()
        print("邮件发送成功")
    except Exception as e:
        write_log = open(log_dir, 'a')
        log = '%s-->%s\n' % (time.strftime('%Y-%m-%d %H:%M:%S'), str(e))
        print(log)
        write_log.write(log)
        write_log.close()

def sysinfo():
    boot_start = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime(psutil.boot_time()))
    time.sleep(0.5)
    cpu_usage = psutil.cpu_percent()
    ram = int(psutil.virtual_memory().total / (1027 * 1024))
    ram_percent = psutil.virtual_memory().percent
    swap = int(psutil.swap_memory().total / (1027 * 1024))
    swap_percent = psutil.swap_memory().percent
    net_sent = psutil.net_io_counters().bytes_sent
    net_recv = psutil.net_io_counters().bytes_recv
    net_spkg = psutil.net_io_counters().packets_sent
    net_rpkg = psutil.net_io_counters().packets_recv
    sysinfo_string = ""
    if __name__ == "__main__":
        bfh = r'%'
        print(" \033[1;32m开机时间：%s\033[1;m" % boot_start)
        print(" \033[1;32m当前CPU使用率：%s%s\033[1;m" % (cpu_usage, bfh))
        print(" \033[1;32m物理内存：%dM\t使用率：%s%s\033[1;m" % (ram, ram_percent, bfh))
        print(" \033[1;32mSwap内存：%dM\t使用率：%s%s\033[1;m" % (swap, swap_percent, bfh))
        print(" \033[1;32m发送：%d Byte\t发送包数：%d个\033[1;m" % (net_sent, net_spkg))
        print(" \033[1;32m接收：%d Byte\t接收包数：%d个\033[1;m" % (net_recv, net_rpkg))
        for i in psutil.disk_partitions():
            print(" \033[1;32m盘符: %s 挂载点: %s 使用率: %s%s\033[1;m" % (i[0], i[1], psutil.disk_usage(i[1])[3], bfh))

        sysinfo_string = '开机时间: ' + boot_start + '\n'
        sysinfo_string += '当前CPU使用率: ' + str(cpu_usage) + '%\n'
        sysinfo_string += '物理内存: ' + str(ram) + 'M\t使用率: ' + str(ram_percent) + '%\t'
        sysinfo_string += 'Swap内存: ' + str(swap) + 'M\t使用率: ' + str(swap_percent) + '%\n'
        return sysinfo_string

    else:
        file = open("sysinfo.log", "a")
        file.write("CPU:%s   \tRAM:%s\tnet_recv:%d\tNet_sent:%d\r\n" % (cpu_usage, ram_percent, net_recv, net_sent))
        file.flush()
        file.close()

def socketmronitor():
    line = "127.0.0.1 8080"
    ip = line.split()[0]
    port = int(line.split()[1])
    while True:
        try:
            sc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

            print(ip, port)
            # 设置超时时间（0.0）
            sc.settimeout(2)
            sc.connect((ip, port))
            timenow = time.localtime()
            datenow = time.strftime('%Y-%m-%d %H:%M:%S', timenow)
            logstr = "%s:%s 连接成功->%s \n" % (ip, port, datenow)
            print(logstr)
            sc.close()
            time.sleep(3)
        except Exception as e:
            file = open("socketmonitor_err.log", "a")
            timenow = time.localtime()
            datenow = time.strftime('%Y-%m-%d %H:%M:%S', timenow)
            logstr = "%s:%s 连接失败->%s \n" % (ip, port, datenow)
            print(logstr)
            file.write(logstr)
            file.close()
            # 发送邮件通知
            send_mail(['348055827@qq.com'], 'Oracle quit', sysinfo())
            # from com.dong4j.zookeeper.zookeeperclient import ZookeeperClient
            # ZookeeperClient.closeconnection()
            exit(-1)
            return False

if __name__ == "__main__":
    socketmronitor()
