#include <stdio.h>
#include <stdlib.h>
#include <readline/readline.h>
#include <readline/history.h>
#include <unistd.h>
#include <string.h>

int main(){
	printf("\n");
	char *username = getlogin();
	char *hostname;
	hostname = malloc(256*sizeof(char));
	int hostnameerror = gethostname(hostname, (size_t)256);
	char *cwd;
	cwd = getcwd(cwd, (size_t)256);
	char prompt[256];
	strcpy(prompt, username);
	strcat(prompt, "@");
	strcat(prompt, hostname);
	strcat(prompt, ": ");
	strcat(prompt, cwd);
	strcat(prompt, " > ");
	int bailout = 0;
	//printf("\nusername: %s\n", username);
	//printf("hostname: %s\n", hostname);
	//printf("working directory: %s\n", cwd);
	while (!bailout) {
		char* reply = readline(prompt);
		/* Note that readline strips away the final \n */

		if (!strcmp(reply, "bye")) {
			bailout = 1;
		} else {

			printf("\nYou said: %s\n\n", reply);
			//
		}
	
		free(reply);
	}
	printf("Bye Bye\n");
	
}
