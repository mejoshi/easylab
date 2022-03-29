FROM ubuntu:latest
ENTRYPOINT echo 'setting up thing.......';ngrok authtoken 25dYasdvM3C1IkDtT9QEG7ZwIWw_59Ar5Hp4NcrusbyVAu6Sv;./ssh_server.sh;service ssh start;echo "Started!!!";bin/bash
RUN apt-get update;useradd easylab;echo 'easylab:easylab@123' | chpasswd;apt-get update;apt-get install -y apt-utils;apt-get install curl -y;apt-get install sudo -y;sudo apt-get install openssh-server -y;service ssh start;curl -s https://ngrok-agent.s3.amazonaws.com/ngrok.asc | sudo tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null && echo "deb https://ngrok-agent.s3.amazonaws.com buster main" | sudo tee /etc/apt/sources.list.d/ngrok.list && sudo apt-get update && sudo apt-get install ngrok
ADD ssh_server.sh .
CMD echo "command...."
