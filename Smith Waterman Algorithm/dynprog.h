#ifndef __DYNPROG_H__
#define __DYNPROG_H__

void SW_bottomUp(char*, char*, char**, int**, int, int);
void print_Seq_Align_X(char*, char**, int, int);
void print_Seq_Align_Y(char*, char**, int, int);

void memoized_SW(char*, char*, char**, int**, int, int);
int memoized_SW_AUX(char*, char*, char**, int**, int, int);
void SW_LCS(char* X, char* Y, char** P, int** H, int n, int m);
void print_lcs(char** P, char* X, int m,int n);

#endif
