#ifndef CHAT_SERVER_HPP
#define CHAT_SERVER_HPP

#include <sys/socket.h>
#include <pthread.h>

class chat_thread {
public:
    chat_thread(int client_socket);
    ~chat_thread();
private:
    void *reciever_function(void *);
    static void broadcast(std::string);

    static  threads;
    static int connections = 0;
    static pthread_cond_t cond;
    static pthread_mutex_t lock; 
    static std::string server_address = "10.0.6.22"
}

#endif // CHAT_SERVER_HPP
