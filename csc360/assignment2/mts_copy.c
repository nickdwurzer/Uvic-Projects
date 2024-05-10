#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <time.h>
#include <stdint.h>

#define MAX_TRAINS 5
pthread_mutex_t start_timer;
pthread_cond_t train_ready_to_load;

long t_s_s = 0;
long t_s_ns = 0; 
float t_s = 0.0; 

// Thread function
void* start_train(void *t)
{
	int i = (intptr_t)t;
	struct timespec time1 = {0};
    // acquire a lock
    pthread_mutex_lock(&start_timer);
    pthread_cond_wait(&train_ready_to_load, &start_timer);
    pthread_mutex_unlock(&start_timer);
    
    clock_gettime(CLOCK_MONOTONIC, &time1);
    long t_t_s = time1.tv_sec;
    long t_t_ns = time1.tv_nsec;
    float t_t = t_t_s+t_t_ns/1000000000;
    printf("Start loading train %d at time %f (at simulation time %f) \n", i, t_t, t_t-t_t);

    pthread_exit(NULL);
}

int main(int argc, char *argv[]){
	//Read input from file and store in dynamic array
	int trains_size = 2;
	char** trains = malloc(trains_size*sizeof(int*));
	if(argc == 2){
		for(int i = 0; i<trains_size; i++) {trains[i] = malloc(4*sizeof(int));};
		FILE * input;
		input = fopen(argv[1], "r");
		char direction;
		int loading_time;
		int crossing_time;
		int i = 0;

		trains[i][0] = i;
		fscanf(input, "%c ", &direction);
		trains[i][1] = direction;
		fscanf(input, "%d ", &loading_time);
		trains[i][2] = loading_time;
		fscanf(input, "%d ", &crossing_time);
		trains[i][3] = crossing_time;
		//printf("%d %c %d %d\n", trains[i][0], trains[i][1], trains[i][2], trains[i][3]);
		
		while(!feof(input)){
			if((i+1) >= trains_size){
				trains_size *= 2;
				char** tmp = realloc(trains, trains_size*sizeof(int*));
				if(tmp){
					trains = tmp;
					for(int j = (trains_size/2); j < trains_size; j++){trains[j] = malloc(4*sizeof(int));}
				}
				else{
					printf("There was a problem reallocating space for trains\n");
				}
			}
			i++;
			trains[i][0] = i;
			fscanf(input, "%c ", &direction);
			trains[i][1] = direction;
			fscanf(input, "%d ", &loading_time);
			trains[i][2] = loading_time;
			fscanf(input, "%d ", &crossing_time);
			trains[i][3] = crossing_time;
			//printf("%d %c %d %d\n", trains[i][0], trains[i][1], trains[i][2], trains[i][3]);
		}

		fclose(input);
	}
	else{
		printf("One argument (the input file name) is expected.\n");
	}


	/*
	struct timespec time1 = {0};
	pthread_t tid[MAX_TRAINS];

	clock_gettime(CLOCK_MONOTONIC, &time1);
	t_s_s = time1.tv_sec;
	t_s_ns = time1.tv_nsec;

	t_s = t_s_s+t_s_ns/1000000000;
	printf("Start the program at %f \n",t_s);

	for(int i =0; i<MAX_TRAINS;i++){
		pthread_create(&tid[i], NULL, start_train, (void *)(intptr_t)i);
		clock_gettime(CLOCK_MONOTONIC, &time1);
		long t_e_s = time1.tv_sec;
		long t_e_ns = time1.tv_nsec;
		float t_e = t_e_s+t_e_ns/1000000000;
		sleep(1);	
		printf("Creating train %d at time %f \n", i, t_e);
		//printf("Creating train %d at time (%lu, %lu) \n", i, t_e_s-t_s_s, t_e_ns-t_s_ns);		
	}

	sleep(1);

	pthread_cond_broadcast(&train_ready_to_load);

	for(int i =0; i<MAX_TRAINS;++i){
		pthread_join(tid[i], NULL);
	}
	*/
	for(int i = 0; i<trains_size; i++) {free(trains[i]);}
	free(trains);
	return 0;

}