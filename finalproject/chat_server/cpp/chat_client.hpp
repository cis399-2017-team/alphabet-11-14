#ifndef CHAT_CLIENT_HPP
#define CHAT_CLIENT_HPP

#include <sys/socket.h>

class chat_client {
public:
    chat_client(std::string server, int port);
    ~chat_client();
private:
    void *listen(void *);
    void send_message(std::string);

    static int server_port = 10000;
    static std::string server_address = "alphabet13.naookiesato.com";
}
#endif // CHAT_CLIENT_HPP
