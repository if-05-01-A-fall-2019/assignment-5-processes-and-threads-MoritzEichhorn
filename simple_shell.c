// Eichhorn Moritz

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

char **convert_to_array(char *);

int main() {
    int status;
    char **command;
    char input[64];
    char* part;
    printf("Enter command: ");
    scanf("%[^\n]s", input);
    part = strtok(input, "&");
    while(part != 0) {
        command = convert_to_array(part);
         if (fork() != 0) {
            waitpid(-1, &status, 0);
        } else {
            execvp(command[0], command);
        }    
        part = strtok(0, "&");
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