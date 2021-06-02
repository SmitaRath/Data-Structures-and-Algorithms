#include <cstdio>
#include <cstdlib>
#include "sort.h"
#include "timer.h"
#include "random_generator.h"

/* 
 * HW 2 part
 */
int string_compare(char* s1, char *s2)
{ 
/*
 * We assume that s1 and s2 are non-null pointers
 */
  int i;

  i = 0;
  while ((s1[i] != 0) && (s2[i] != 0) && (s1[i] == s2[i]))
    i++;

  if (s1[i] == s2[i])
    return 0;
  else
    {
      if (s1[i] < s2[i])
	return -1;
      else
	return 1;
    }
} /*>>>*/

void insertion_sort(char** A, int l, int r)
{ 
  int i;
  char* key;

  for (int j = l+1; j <= r; j++)
    {
      key = A[j];
      i = j - 1;

      while ((i >= l) && (string_compare(A[i], key) > 0))
        {
	  A[i+1] = A[i];
	  i = i - 1;
	}

      A[i+1] = key;
    }
}

void insertion_sort_digit(char** A, int* A_len, int l, int r, int d)
{
	  int i;
	  //variable for storing key, length of the key and character position value
	  char* key;
	  int keyPosLength;
	  int keyLength;


	  for (int j = l+1; j <= r; j++)
	    {
		  //storing key, length of the key and character position value
	      key = A[j];
	      keyLength=A_len[j];
	      //comparing the length if greater than d then storing int value otheriwse 0
	      if(A_len[j]>d)
	    	  keyPosLength=int(A[j][d]);
	      else
	    	  keyPosLength=0;
	      i = j - 1;

	      //comparing the int value of ith position d charater with keyPoslength
	      while ((i >= l) && (int(A[i][d]) > keyPosLength))
	        {
		  A[i+1] = A[i];
		  A_len[i+1]=A_len[i];
		  i = i - 1;
		}

	      //storing length and key value at correct position
	      A[i+1] = key;
	      A_len[i+1]=keyLength;
	    }

}

void counting_sort_digit(char** A, int* A_len, char** B, int* B_len, int n, int d)
{
	//defining new arrays for sorting
	int size=256;
	int* C = new int[size];
	int* D = new int[n];

	//initializing C array with 0
	for(int i=0;i<size;i++){
		C[i]=0;
	}


	// building D array for the int value of position d and incrementing C array for D[] value
	for(int i=0;i<n;i++)
	{
	 if(A_len[i]>d)
			D[i]=int(A[i][d]);
		else
			D[i]=0;

		C[D[i]]=C[D[i]]+1;
	}


	//adding previous position value to next position value
	for(int i=1;i<size;i++)
			C[i]=C[i-1]+C[i];


	//constructing sorting array according to dth position and also assigning length
	for(int i=n-1;i>=0;i--)
	{
		B[C[D[i]]-1]=A[i];
		B_len[C[D[i]]-1]=A_len[i];
		C[D[i]]=C[D[i]]-1;
	}

	//deleting newly created arrays.
	delete[] C;
	delete[] D;

}

void radix_sort_is(char** A, int* A_len, int n, int m)
{ 

	int max=0;
		//calculating the maximum value of the string from all the elements
		for(int i=0;i<n;i++){
			if(max<A_len[i])
				max=A_len[i];
		}

		//looping from max value to 0 for position and calling insertion sort for every position of d
		for(int i=max-1;i>=0;i--){
			insertion_sort_digit(A,A_len,0,n-1,i);
		}
}

void radix_sort_cs(char** A, int* A_len, int n, int m)
{ 
	char* B[n];
	int B_len[n];
	int max=0;
	//calculating the maximum value of the string from all the elements
			for(int i=0;i<n;i++){
				if(max<A_len[i])
					max=A_len[i];
			}

	//looping from max value to 0 for position and calling counting sort for every position of d
	for(int i=max-1;i>=0;i--){
		counting_sort_digit(A,A_len,B,B_len,n,i);

		//copyng B array which returned from counting sort to A
		for(int k=0;k<n;k++){
			A[k]=B[k];
			A_len[k]=B_len[k];
		}
	}
}

/*
 * Simple function to check that our sorting algorithm did work
 * -> problem, if we find position, where the (i-1)-th element is 
 *    greater than the i-th element.
 */
bool check_sorted(char** A, int l, int r)
{
  for (int i = l+1; i < r; i++)
    if (string_compare(A[i-1],A[i]) > 0)
      return false;
  return true;
}
