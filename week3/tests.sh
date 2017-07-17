#!/bin/bash

sudo apt-get purge ssh -y
sudo puppet agent -t
sudo service ssh stop
sudo puppet agent -t
sudo rm /etc/ssh/sshd_config
sudo puppet agent -t
sudo rm /home/ubuntu/.ssh/authorized_keys
sudo puppet agent -t
