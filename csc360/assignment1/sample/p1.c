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
	//while the user has not typed 'exit'
	while (!bailout) {
		getPrompt(myPrompt);
		char *reply = readline(myPrompt);
		if (!strcmp(reply, "exit")) {
			bailout = 1;
		} 
		else{
            char *tokens[64];
            tokenize(tokens, reply); 
			//change directory if user has typed 'cd <path>'
			if(strcmp(tokens[0],"cd")==0){
				changeDirectory(tokens[1]);
			}
			//list background processes if the user typed ''bglist'
			else if(strcmp(tokens[0], "bglist")==0){
					printf("The program should list background processes now\n");
			}
			//execute a background process if the user has typed 'bg <program>'
			else if(strcmp(tokens[0], "bg")==0){
				printf("The program should execute a background process now\n");
			}
			//else try to execute the program which the user typed
			else{
				forkProcess(tokens);
				
			}
		}
		free(reply);
	}
	printf("Bye Bye\n"); 
}



/*
 * Purpose: This function changes the current directory of the user to the directory specified by the variable *path.
 * If path points to NULL or '~' then the directory will be set to the user's HOME environment variable.
 * Parameters: A pointer to a string representing the path to the desired directory.
 * Return value: void
 */
void changeDirectory(char *path){
	if(path == NULL || strcmp(path, "~") == 0){
		char* home = getenv("HOME");
		if(chdir(home) !=0){
			printf("An error occured trying to reach this directory, please check the path\n");
		}
	}
	else{
		if(chdir(path) != 0){
			printf("An error occured trying to reach this directory, please check the path\n");
		}
	}
}

/*
 * Purpose: This function creates a process which run sin the forground (parent waits for child to finish execution).
 * If an error occurs while executing the command, a general error message is displayed and the child process is killed.
 * Parameters: A pointer to a string of pointers.  Each pointer in the string is a character string argument to the program which will be executed.
 * Return value: void
 */
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

/*
 * Purpose: This function tokenizes a given line and stores the tokens in a string of pointers called tokens.
 * Parameters: A pointer to a string of pointers to store each token.  A line to be tokenized.
 * Return value: void
 */
void tokenize(char** tokens, char* line){
    tokens[0] = strtok(line, " \n");
    int i = 0;
    while(tokens[i] != NULL){
        tokens[i+1] = strtok(NULL, " \n");
        i++;
    }
}

/*
 * Purpose: This function returns a string containing the username, hostname and working directory of the current user.
 * Parameters: A pointer prompt to store the resulting string.
 * Return value: void
 */
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
}
