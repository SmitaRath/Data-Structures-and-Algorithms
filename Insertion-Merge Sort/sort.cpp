#include <cstdio>
#include <cstdlib>
# include <iostream>
#include "sort.h"
#include <vector>
#include<memory>

using namespace std;

//defined struct for storing index and length
struct ArrayStructData
	{
	   int length;
	   int index;

	};

int ivector_length(int* v, int n)
{
  int sum;

  sum = 0;
  for (int i = 0; i < n; i++)
    sum += (v[i] < 0) ? -v[i] : v[i];

  return sum;
}

/*
 * insertion sort
 */
void insertion_sort(int** A, int n, int l, int r)
{ 
  int i;
  int* key;

  for (int j = l+1; j <= r; j++)
    {
      key = A[j];
      i = j - 1;

      while ((i >= l) && (ivector_length(A[i], n) > ivector_length(key, n)))
        {
	  A[i+1] = A[i];
	  i = i - 1;
	}

      A[i+1] = key;
    }
}

/*
*   TO IMPLEMENT: Improved Insertion Sort for problem 1.
*/
void insertion_sort_im(int** A, int n, int l, int r)
{ 

	//declaring and initializing array to store the length of inner arrays
	int* len = new int[r];

	//storing the length of inner array inside declared array
	for(int j=l;j<=r;j++){
		len[j]=ivector_length(A[j],n);
	}


	  int i;
	  int* key;
	  int lengthVar;

	  //running insertion sort algorithm
	  for (int j = l+1; j <= r; j++)
	    {
		  //storing the current array and length of the array
	      key = A[j];
	      lengthVar = len[j];
	      i = j - 1;

	      //comparing if the current array length is less than previous element
	      while ((i >= l) && (len[i]>lengthVar))
	       {
	    	//if yes then swapping the array as well as the length of the array
	    	  A[i+1] = A[i];
	    	  len[i+1]=len[i];
	    	  i = i - 1;
	       }

	      //storing the array and length at the desired postion
	      A[i+1] = key;
	      len[i+1]=lengthVar;
	    }

}


//helper method to merge the left and right subpart
void merge(struct ArrayStructData structArray[], int l, int m, int h){

	//calcualting left subarray length and right subarray length
	int n1= m-l+1;
	int n2=h-m;

	//creating left and right subarray
	ArrayStructData* LeftArray = new ArrayStructData[n1];
	ArrayStructData* RightArray = new ArrayStructData[n2];

	//assigning values to left and right subarray
	for(int i=0;i<n1;i++){
		LeftArray[i]=structArray[l+i];
	}
	for(int j=0;j<n2;j++){
		RightArray[j]=structArray[m+1+j];
	}
	int i =0;
	int j=0;
	int k=l;

	//checking if left element is grrater then putting in the final array
	//else if right element id greater then putitng in final array
	while(i<n1 && j<n2){
		if(LeftArray[i].length<=RightArray[j].length){
			structArray[k]=LeftArray[i];
			i++;
		}
		else{
			structArray[k]=RightArray[j];
			j++;
		}
		k++;
	}

	//if elments are left in left and righ array then storing in final array
	while(i<n1){
		structArray[k]=LeftArray[i];
		i++;
		k++;
	}
	while(j<n2){
		structArray[k]=RightArray[j];
		j++;
		k++;
	}

	//deleting the left and right array
	delete[] LeftArray;
	delete[] RightArray;
}

//helper method to do merge sort
void mergeSort(struct ArrayStructData structArray[], int l, int h){
	//if low index is greater than high index then return
	if(l>=h){
	return;
	}
		//calculating mid value
		int mid=l + (h-l)/2;
		//recursively calling left array
		mergeSort(structArray, l, mid);
		//recursively calling right array
		mergeSort(structArray, mid+1, h);
		//calling merge method to merge left and right array
		merge(structArray, l, mid, h);

}



/*
*   TO IMPLEMENT: Improved Merge Sort for problem 2.
*/
void merge_sort(int** A, int n, int p, int r)
{
	int index;
	//declaring struct type variable to store length and index
	ArrayStructData* len;
		len= new ArrayStructData[r+1];

	//declaring newArray to copy the sorted array
	int** newArray;
		newArray = new int*[r+1];

	for (int i = 0; i <= r; i++){
		newArray[i] = new int[n];
	}


	//storing length and index into struct array
		for(int j=p;j<=r;j++){
			len[j].index=j;
			len[j].length=ivector_length(A[j],n);
		}


		//calling mergeSort on the struct array to sort according to length
		mergeSort(len,0,r);

		//creating sorted array from struct array by utilizing index value
		for(int j=0;j<=r;j++){
			index=len[j].index;
			 for (int i = 0; i < n; i++)
				 newArray[j][i] = A[index][i];
		}

		//calling the sorted array into final array
		for(int j=0;j<=r;j++){
			for (int i = 0; i < n; i++)
					A[j][i] = newArray[j][i];
		}


		//deleting all the newly created aaray
		for (int i = 0; i <= r; i++){
		    delete[] newArray[i];
		  }

		delete[] newArray;
        delete[] len;
}


/*
 * Simple function to check that our sorting algorithm did work
 * -> problem, if we find position, where the (i-1)-th element is 
 *    greater than the i-th element.
 */
bool check_sorted(int** A, int n, int l, int r)
{
  for (int i = l+1; i <= r; i++)
    if (ivector_length(A[i-1], n) > ivector_length(A[i], n))
      return false;
  return true;
}


/*
 * generate sorted/reverse/random arrays of type hw1type
 */
int** create_ivector(int n, int m)
{
  int** iv_array;

  iv_array = new int*[m];
  for (int i = 0; i < m; i++)
    iv_array[i] = new int[n];

  return iv_array;
}

void remove_ivector(int** iv_array, int m)
{
  for (int i = 0; i < m; i++){
    delete[] iv_array[i];
  }
  delete[] iv_array;
}

int** create_sorted_ivector(int n, int m)
{
  int** iv_array;

  iv_array = create_ivector(n, m);
  for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
      iv_array[i][j] = (i+j)/n;

  return iv_array;
}

int** create_reverse_sorted_ivector(int n, int m)
{
  int** iv_array;

  iv_array = create_ivector(n, m);
  for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
      iv_array[i][j] = ((m-i)+j)/n;

  return iv_array;
}

int** create_random_ivector(int n, int m, bool small)
{
  random_generator rg;
  int** iv_array;

  iv_array = create_ivector(n, m);
  for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
      {
	rg >> iv_array[i][j];
	if (small)
	  iv_array[i][j] %= 100;
	else
	  iv_array[i][j] %= 65536;
      }

  return iv_array;
}

