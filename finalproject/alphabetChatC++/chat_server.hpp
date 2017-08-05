#ifndef CHAT_SERVER_HPP
#define CHAT_SERVER_HPP

class chat_server {
public:
    chat_server();
    ~chat_server();
private:
    std::vector<pthread_t> threads;
    void *reciever_function(void *);
    void broadcast(std::string);
    static std::string server_address = "10.0.6.22"
}

#endif // CHAT_SERVER_HPP
