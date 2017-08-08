#ifndef CHAT_CLIENT_H
#define CHAT_CLIENT_H

#include <sys/socket.h>
#include <pthread.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <arpa/inet.h>

void *listen(void *arg);
void send_message(char *message);

static int
#endif // CHAT_CLIENT_H
