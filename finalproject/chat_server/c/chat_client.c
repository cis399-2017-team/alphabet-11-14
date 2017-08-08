#include "chat_client.h"

int server_port = 10000;
const char *alphabet_server_address = "alphabet13.naookiesato.com";

int main(int argc, char *argv[]) {
    //TODO: parse arguments, later date

    int client_socket = 0;
    struct sockaddr_in server_address;
    
    if (client_socket = socket(AF_INET, SOCK_STREAM, 0) < 0) {
        printf("error at client socket creation\n");
        exit(-1);
    }

    memset(&server_address, 0, sizeof(server_address));
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = 
    server_address.sin_port = htons(server_port);

    if (connect(client_socketm, , ) < 0) {
        printf("error connecting to server\n");
        exit(-1);
    } 
    if (close(client_socket) < 0) {n
        printf("error closing client socket\n");
        exit(-1);
    }
    return 0;
}

void *listen(void *arg) {
    return arg;
}

void send_message(char *message) {

}

