//Code skeleton adapted from sample.c, distributed by Professor Jianping Pan at the University of Victoria
#include <stdio.h>
#include <stdlib.h>
#include <readline/readline.h>
#include <readline/history.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <signal.h>
#include <sys/wait.h>

void getPrompt(char*);
void tokenize(char**, char*);
void forkProcess(char**);
void changeDirectory(char*);


int main(){
	char myPrompt[256];
	int bailout = 0;
	while (!bailout) {
		getPrompt(myPrompt);
		char *reply = readline(myPrompt);
		/* Note that readline strips away the final \n */
		if (!strcmp(reply, "bye")) {
			bailout = 1;
		} 
		else{
            char *tokens[64];
            tokenize(tokens, reply); 
            /*for(int i = 0; tokens[i] != NULL; i++){
                printf("\nToken[%d] = %s", i, tokens[i]);
            }
            printf("\n");
			printf("\nYou said: %s\n\n", reply);
			*/
			if(strcmp(tokens[0],"cd")==0){
				changeDirectory(tokens[1]);
			}
			else if(strcmp(tokens[0], "bg")==0){
				printf("The program should execute a background process now\n");
			}
			else if(strcmp(tokens[0], "bglist")==0){
					printf("The program should list background processes now\n");
			}
			else{
				//printf("The program should execute the given process\n");
				forkProcess(tokens);
				
			}
		}
		free(reply);
	}
	printf("Bye Bye\n"); 
}

void changeDirectory(char *path){
	if(path == NULL || strcmp(path, "~") == 0){
		char* home = getenv("HOME");
		printf("%s\n", home);
	}
	else{
		chdir(path);
	}
}

void forkProcess(char** tokens){
	pid_t p = fork();
	if(p==0){
		int exec = execvp(tokens[0], tokens);
		if(exec == -1){
			printf("Command not found\n");
			kill(getpid(), 9);
		}
	}
	else{
		waitpid(p, NULL, 0);
	}
}

void tokenize(char** tokens, char* line){
    tokens[0] = strtok(line, " \n");
    int i = 0;
    while(tokens[i] != NULL){
        tokens[i+1] = strtok(NULL, " \n");
        i++;
    }
}

void getPrompt(char* prompt){
	char *username = getlogin();
	char *hostname;
	hostname = malloc(256*sizeof(char));
	gethostname(hostname, (size_t)256);
	char *cwd;
    cwd = malloc(256*sizeof(char));
	getcwd(cwd, (size_t)256);
	strcpy(prompt, username);
	strcat(prompt, "@");
	strcat(prompt, hostname);
	strcat(prompt, ": ");
	free(hostname);
	strcat(prompt, cwd);
	strcat(prompt, " > ");
	//printf("\nusername: %s\n", username);
	//printf("hostname: %s\n", hostname);
	//printf("working directory: %s\n", cwd);
}
