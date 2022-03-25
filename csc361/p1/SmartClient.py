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
		user_input = sys.argv[1]
		split_input = re.split(r"/",user_input, maxsplit=1)
		hostname = split_input[0]
		if(1<len(split_input)):
			path = "/" + split_input[1]
		else:
			path = "/"
	tryhttp(hostname, path)

#Purpose:
#Arguments:
#Returns:
def get_response_code(s):
	response_code_re = re.compile(r".*?HTTP/....(\d\d\d)")	
	response_code = response_code_re.search(s)
	return response_code[1]
#Purpose:
#Arguments:
#Returns:
def get_redirect(s):
	redirect_re = re.compile(r"Location: (https?).?.?.?(.*?)/(.*?)\r\n")
	redirect = redirect_re.search(s)
	return redirect
	
#Purpose:
#Arguments:
#Returns:
def tryhttp(hostname, path):
	with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
		sock.connect((hostname, 80))
		message = "GET " + path + " HTTP/1.1\r\nHost: " + hostname + "\r\n\r\n"
		sock.send(bytes(message, "utf-8"))
		reply = sock.recv(1024).decode("utf-8")
		print("request...\n"+message+"\n...Endrequest\n")
		print("reply...\n" + reply+"\n...Endreply\n")
		response_code = get_response_code(reply)
		if(response_code=="200"):
			print(tryhttp2(hostname,path))
			print("list of cookies:\n"+get_cookies(reply))
		elif(response_code=="301" or response_code=="302"):
			redirect = get_redirect(reply)
			if(redirect[1]=="https"):
				print("redirecting to https...")
				print(tryhttp2(redirect[2], "/" + redirect[3]))
			else:
				print("redirecting to http...")
				tryhttp(redirect[2], "/" + redirect[3])
		elif(response_code =="401"):
			print("401")
			print("This site is password protected")
		elif(response_code =="404"):
			print("not found")
		else:
			print("an unknown response code was received")



#Purpose: This function returns a list of cookies.  Included in the cookie is
 #the cookie name, expire time, and domain.
#Arguments: A string
#Returns: A string of formatted cookies.
def get_cookies(s):
	cookie_re = re.compile(r"(Set-Cookie:.*?);.*?(expires=.*?);.*?(domain=.*?);")
	cookies = cookie_re.findall(s)
	result = ""
	for cookie in cookies:
		result += "\n" + cookie[0]
		for i in cookie[1:]:
			result += ",  " + i
	return result

#Purpose: This function returns True if the server supports http2, else False.
#Arguments: A hostname or IP-address
#Returns: True if the server supports http2, else False.
def tryhttp2(hostname, path):
	with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
		context = ssl.create_default_context()
		context.set_alpn_protocols(["h2", "http/1.1"])
		with context.wrap_socket(sock, server_hostname=hostname) as ssock:
			ssock.connect((hostname, 443))
			"""message = "PRI *m HTTP/2.0\r\n\r\nSM\r\n\r\n"
			#print()
			ssock.send(bytes(message, "utf-8"))
			reply = ssock.recv(1024).decode("utf-8")
			print(reply)
			response_code = get_response_code(reply)
			if(response_code=="200"):
				print(get_cookies(reply))
			elif(response_code=="301" or response_code=="302"):
				redirect = get_redirect(reply)
				if(redirect[1]=="https"):
					print(tryhttp2(redirect[2], path))
				else:
					tryhttp(redirect[2], path)
			elif(response_code =="401"):
				print("401")
				print("This site is password protected")
			elif(response_code =="404"):
				print("not found")
			else:
				print("an unknown response code was received")
"""
			if (ssock.selected_alpn_protocol() == "h2"):
				return "Supports http2: Yes"
			else:
				return "Supports http2: No"


if __name__ == "__main__":
	main()
