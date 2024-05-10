#!/usr/bin/env python3
import sys
import socket
import ssl
import re

def main():
    #Check if the user input the correct number of arguments.
    if(len(sys.argv) != 2):
        print("Please structure your command in the form 'python3 SmartClient.py www.WebsiteHostName.com'  \nNumber of arguments expected: 1  \nNumber of arguments received:", len(sys.argv) - 1)
    
    else:
        hostname = sys.argv[1]
        #list cookies
        with socket.create_connection((hostname, 80)) as s:
            message = "GET /  HTTP/1.1\r\nHOST: " + hostname + "\r\n\r\n"
            print(message)
            s.send(bytes(message, "utf-8"))
            reply = s.recv(1024).decode("utf-8")
            cookie_re = re.compile(r"(Set-Cookie:.*?;).*?(expires=.*?;).*?(domain=.*?;)")
            cookies = cookie_re.findall(reply)
            for cookie in cookies:
                print(cookie)
            print("\n\n", reply)
        #handle redirects
        #password protected?
        #checkhttp2(hostname)
#TODO
def checkhttp2(hostname):
    with socket.create_connection((hostname, 443)) as sock:
            context = ssl.create_default_context()
            with context.wrap_socket(sock, server_hostname=hostname) as ssock:
                ssock.send(b"GET /index.html HTTP/1.0\n\n")
                print(type(ssock.recv().decode("utf-8")))


if __name__ == "__main__":
    main()
