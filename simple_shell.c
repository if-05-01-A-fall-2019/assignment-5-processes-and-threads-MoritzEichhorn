// Eichhorn Moritz

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

char **convert_to_array(char *);
int get_next_position(char* input);

int main() {
    int status;
    char **command;
    char input[64];
    char inputCpy[64];
    char* part;
    char del[2] = "&";
    
    printf("Enter command: ");
    scanf("%[^\n]s", input);
    
    strcpy(inputCpy, input + get_next_position(input));
    part = strtok(input, del);
    while(part != 0) {
        command = convert_to_array(part);
         if (fork() != 0) {
            waitpid(-1, &status, 0);
            strcpy(input, inputCpy);
            strcpy(inputCpy, inputCpy + get_next_position(inputCpy));
            part = strtok(input, del);
            
        } else {
            execvp(command[0], command);
            part = 0;
        }    
        free(command);
    }
    free(command);
    
    return 0;
}

char **convert_to_array(char *input) {
    char **command = malloc(7 * sizeof(char *));
    char *parsed;
    int i = 0;

    parsed = strtok(input, " ");
    while (parsed != 0) {
        command[i] = parsed;
        parsed = strtok(0, " ");
        i++;
    }

    command[i] = 0;
    return command;
}

int get_next_position(char* input) {
    for(int i = 0; i < strlen(input); i++) {
        if(input[i] == '&') {
            return i + 1;
        }
    }
    return -1;
}