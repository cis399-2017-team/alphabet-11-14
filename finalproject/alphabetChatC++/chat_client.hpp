#ifndef CHAT_CLIENT_HPP
#define CHAT_CLIENT_HPP

#include <>

class chat_client {
public:
    chat_client();
    ~chat_client();
private:
    void *listen(void *);
    void send_message(std::string);

    static int server_port = 10000;
    static std::string server_address = "alphabet13.naookiesato.com";
}
#endif // CHAT_CLIENT_HPP
