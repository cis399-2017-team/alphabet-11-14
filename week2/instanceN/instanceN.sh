#1 Private IP: 10.0.6.22
#2 Go to the EC2 Console. Select the target instance and IPv4 Public IP is the second item in the right column.
#3 Provide the output of a command showing how much disk space one of your instances has.
echo "df"
echo "df -h"
#4 Provide the output of a command showing how much memory one of your instances has.
echo "free"
echo "free -h"
#5 Provide a listing showing all the processes running in one of your instances.
echo "ps -a"
echo "ps -A"
#6 Provide the output of a command showing the network interface configuration of your instance (this should show its private IP address).
echo "ifconfig"
echo "ifconfig -a"
#7 Provide the output of a command showing the routing table of your instance.
#echo "route -v" 
#or
echo "netstat -r"
#8 Provide the output of a command showing all of the open network connections and ports on one of your instances.
echo "netstat -a"
#9 Provide a listing of all of the software packages installed on one your instances.
echo "dpkg --list"
#10 Provide transcripts showing the output of the commands you use to update your package database and upgrade packages on each of your instances.
#update package dependencies
echo "sudo apt-get update"
#get updated packages
echo "sudo apt-get upgrade -y"
echo "exit"
