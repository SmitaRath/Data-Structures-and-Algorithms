#include <cstring>
#include <iostream>
#include <limits.h>
#include<algorithm>

#include "dynprog.h"

using namespace std;


/*
 * Bottom up implementation of Smith-Waterman algorithm
 */
void SW_bottomUp(char* X, char* Y, char** P, int** H, int n, int m){

	int p1,p2,p3=0;
    int min=INT_MAX;

	for(int i=0;i<=n;i++){
		P[i][0]='0';
		H[i][0]=0;
	}


	for(int j=0;j<=m;j++){
			P[0][j]='0';
			H[0][j]=0;
		}



	for(int i=1;i<=n;i++){
		for(int j=1;j<=m;j++){
			if(X[i]==Y[j])
				p1=H[i-1][j-1]+2;
			else
				p1=H[i-1][j-1]-1;
			p2=H[i-1][j]-1;
			p3=H[i][j-1]-1;

			H[i][j]=max({p1,p2,p3});


			if(H[i][j]==p1)
				P[i][j]='D';
			else
				if(H[i][j]==p2)
					P[i][j]='U';
				else
					P[i][j]='H';
		}
	}


	cout<<" P Matrix============== "<<endl;
	for(int i=0;i<=n;i++){
		for(int j=0;j<=m;j++){
			cout<<P[i][j]<<" ";
		}
		cout<<endl;
	}

	cout<<" H Matrix============== "<<endl;
	for(int i=0;i<=n;i++){
			for(int j=0;j<=m;j++){
				if(min>H[i][j])
					min=H[i][j];
				cout<<H[i][j]<<" ";
			}
			cout<<endl;

	}
	cout<<"Minimum value in H matrix"<<min<<endl;

}




/*
 * Top-down with memoization implementation of Smith-Waterman algorithm
 */
void memoized_SW(char* X, char* Y, char** P, int** H, int n, int m){
	int min=INT_MAX;

	for(int i=0;i<=n;i++){
			P[i][0]='0';
			H[i][0]=0;
	}


		for(int j=0;j<=m;j++){
				P[0][j]='0';
				H[0][j]=0;
		}


		memoized_SW_AUX(X,Y,P,H,n,m);

		cout<<" P Matrix============== "<<endl;
		for(int i=0;i<=n;i++){
				for(int j=0;j<=m;j++){
					cout<<P[i][j]<<" ";
				}
				cout<<endl;
			}

		cout<<" H Matrix============== "<<endl;
		for(int i=0;i<=n;i++){
				for(int j=0;j<=m;j++){
					cout<<H[i][j]<<" ";
					if(min>H[i][j])
						min=H[i][j];
				}
			cout<<endl;
		}


		cout<<"Minimum value in H matrix"<<min<<endl;
}

/*
 * Auxilary recursive function of top-down with memoization implementation of Smith-Waterman algorithm
 */
int memoized_SW_AUX(char* X, char* Y, char** P, int** H, int n, int m){

	int p1,p2,p3=0;

	if(n==0 || m==0)
		return 0;

	if(H[n][m]==0){

		if(X[n]==Y[m])
			p1=memoized_SW_AUX(X,Y,P,H,n-1,m-1)+2;
		else
			p1=memoized_SW_AUX(X,Y,P,H,n-1,m-1)-1;

		p2=memoized_SW_AUX(X,Y,P,H,n-1,m)-1;
		p3=memoized_SW_AUX(X,Y,P,H,n,m-1)-1;

		H[n][m]=max({p1,p2,p3});

		if(H[n][m]==p1)
			P[n][m]='D';
		else
			if(H[n][m]==p2)
				P[n][m]='U';
			else
				P[n][m]='H';

	return H[n][m];
	}

	else
		return H[n][m];

}




/*
 * Print X'
 */

void print_Seq_Align_X(char* X, char** P, int n, int m){

	if(n==0 || m==0)
		return;
	if(P[n][m]=='D'){

		print_Seq_Align_X(X,P,n-1,m-1);
        cout<<X[n];
	}
	else
		if(P[n][m]=='H'){
            print_Seq_Align_X(X,P,n,m-1);
            cout<<'-';
		}
		else
		{
			print_Seq_Align_X(X,P,n-1,m);
			cout<<X[n];
		}
    return;
}



/*
 * Print Y'
 */
void print_Seq_Align_Y(char* Y, char** P, int n, int m){
	if(n==0 || m==0)
			return;
		if(P[n][m]=='D'){
			print_Seq_Align_Y(Y,P,n-1,m-1);
			cout<<Y[m];
		}
		else
			if(P[n][m]=='H'){
				print_Seq_Align_Y(Y,P,n,m-1);
				cout<<Y[m];
			}
			else
			{
				print_Seq_Align_Y(Y,P,n-1,m);
							cout<<"-";
			}
	    return;
	
}


//Question 15.4.1 LCS
void SW_LCS(char* X, char* Y, char** P, int** H, int n, int m){
	for(int i=0;i<=n;i++){
			P[i][0]='0';
			H[i][0]=0;
		}


		for(int j=0;j<=m;j++){
				P[0][j]='0';
				H[0][j]=0;
			}

	for(int i=1;i<=n;i++){
		for(int j=1;j<=m;j++){
			if(X[i]==Y[j]){
				H[i][j]=H[i-1][j-1]+1;
				P[i][j]='D';
			}
			else
				if( H[i-1][j] >= H[i][j-1])
				{
					H[i][j]=H[i-1][j];
					P[i][j]='U';
				}
				else{
					H[i][j]=H[i][j-1];
					P[i][j]='H';
				}
		}

	}

	cout<<" P Matrix============== "<<endl;
	for(int i=0;i<=n;i++){
		for(int j=0;j<=m;j++){
			cout<<P[i][j]<<" ";
		}
		cout<<endl;
	}

	cout<<" H Matrix============== "<<endl;
	for(int i=0;i<=n;i++){
			for(int j=0;j<=m;j++){
				cout<<H[i][j]<<" ";
			}
			cout<<endl;
		}


}

void print_lcs(char** P, char* X,int m,int n){
	if(m==0 || n==0)
		return;
	if(P[m][n]=='D'){
		print_lcs(P,X,m-1,n-1);
		cout<<X[m];
	}
		else
		{
			if(P[m][n]=='U'){
				print_lcs(P,X,m-1,n);
			}
			else
				print_lcs(P,X,m,n-1);
		}

}
