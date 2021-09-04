#include "graph.h"
#include <list>
#include <iostream>

using namespace std;

/*
 * constructor/destructor
 */
graph::graph(int n)
{ /*<<<*/
  no_vert = (n > 0) ? n : 1;

/*
 * allocate adjacency matrix
 */
  m_edge = new int*[no_vert];
  for (int i = 0; i < no_vert; i++)
    m_edge[i] = new int[no_vert];

  initialize();
} /*>>>*/

graph::~graph()
{ /*<<<*/ 
/*
 * delete nil element and all other allocated nodes
 */
  for (int i = 0; i < no_vert; i++)
    delete[] m_edge[i];
  delete[] m_edge;
} /*>>>*/


void graph::initialize()
{ /*<<<*/
/*
 * initialize adjacency matrix
 * -> use infinity to allow 0 weight edges
 */
  for (int i = 0; i < no_vert; i++)
    for (int j = 0; j < no_vert; j++)
      m_edge[i][j] = INT_MAX;




} /*>>>*/

void graph::permutation(int* perm, int n)
{ /*<<<*/
  random_generator rg;
  int p_tmp, p_pos;

  for (int i = 0; i < n; i++)
    perm[i] = i;
  
  for (int i = 0; i < n; i++)
    {
      rg >> p_pos;
      p_pos = (p_pos % (n - i)) + i;

      p_tmp = perm[i];
      perm[i] = perm[p_pos];
      perm[p_pos] = p_tmp;
    }
} /*>>>*/

void graph::output(int** m_out, int** m_out_2)
{ /*<<<*/
  if (!m_out)
    m_out = m_edge;

  cout << "[";
  for (int i = 0; i < no_vert; i++)
    {
      cout << "[\t";
      for (int j = 0; j < no_vert; j++)
  if (m_out[i][j] == INT_MAX)
    cout << "inf\t";
  else
    cout << m_out[i][j] << "\t";

      if (m_out_2)
  {
    cout << "]\t\t[\t";
    for (int j = 0; j < no_vert; j++)
      if (m_out_2[i][j] == INT_MAX)
        cout << "inf\t";
      else
        cout << m_out_2[i][j] << "\t";
  }

      cout << "]" << endl;
    }
  cout << "]" << endl;
} /*>>>*/


// TODO: Implement problem 1
void graph::generate_random(int n_edges, int max_weight)
{

	  int *verArray = new int[no_vert];
	  int edgeCount = 0;
	  int randomGeneratedWeight;
	  random_generator rand_gen;
	  int firstVertex;
	  int secondVertex;

	  while(true){

		  //if the no of edges count is greater than the specified edges then we will break the loop

	    if(edgeCount >= n_edges)
	      break;

	    //calling the permutation function to randomly pick the vertices while creating new edges

		  permutation(verArray, no_vert);
	      int index = 0;

	      //running the loop till newly edge count is less than max edges and index is less than vertices -2 because vertices
	      //start from 0 to n-1 and we are selecting two vertices so
	      while (edgeCount < n_edges && index < (no_vert - 2))
	      {
	      // randomly picking two vertices array which is formed using permutation
	      firstVertex = verArray[index];
	      secondVertex = verArray[index + 1];

	      //checking if the individual path does not contain a cycle, two edges cannot have edge towrds each other
	      if (m_edge[firstVertex][secondVertex] == INT_MAX &&
	    		  m_edge[secondVertex][firstVertex]==INT_MAX
	      )

	      {

	    	// generating a random number
	        rand_gen >> randomGeneratedWeight;
	        // generating weight in the interval [-w,w]
	        randomGeneratedWeight = randomGeneratedWeight % (2*max_weight + 1) +  -1* max_weight;
	        //generating new edge and assigning weight to the newly added edge
	        m_edge[firstVertex][secondVertex] = randomGeneratedWeight;
	        //increasing the edge count
	        edgeCount=edgeCount+1;
	      }
	      index=index+1;
	    }
	  }


	  delete[] verArray;

}

void graph::initSingleSource(int s,int*& v_d, int*& v_pi){


	for(int i=0;i<no_vert;i++){
			v_d[i]=INT_MAX;
			v_pi[i]=INT_MAX;
	}
	v_d[s]=0;


}

void graph::relax(int u, int v, int w, int*& v_d, int*& v_pi){
	if(v_d[v] > (v_d[u] + w)){
		v_d[v] = v_d[u] + w;
		v_pi[v]=u;
	}
}

// TODO: Implement problem 3
bool graph::bellman_ford(int s, int*& v_d, int*& v_pi)
{
	v_d=new int[no_vert];
	v_pi = new int[no_vert];

	initSingleSource(s,v_d,v_pi);

	for(int k=0;k<no_vert-1;k++){
		for(int i=0;i<no_vert;i++){
			for(int j=0;j<no_vert;j++){
				if(m_edge[i][j]!=INT_MAX)
					relax(i,j,m_edge[i][j],v_d,v_pi);

			}
		}
	}

	for(int i1=0;i1<no_vert;i1++){
			for(int j1=0;j1<no_vert;j1++){
				if(m_edge[i1][j1]!=INT_MAX){
					if(v_d[j1] > (v_d[i1] + m_edge[i1][j1])){
						return false;
					}
				}
			}
		}
	return true;
}

// TODO: Implement problem 4
void graph::floyd_warshall(int**& d, int**& pi)
{


	/*int m[5][5]= {{INT_MAX, 3, 8, INT_MAX, -4},
							{INT_MAX, INT_MAX, INT_MAX, 1, 7},
							{INT_MAX, 4, INT_MAX, INT_MAX, INT_MAX},
							{2, INT_MAX, -5, INT_MAX, INT_MAX},
							{INT_MAX ,INT_MAX, INT_MAX, 6, INT_MAX}};*/


	d = new int*[no_vert];
	  for (int i = 0; i < no_vert; i++)
	    d[i] = new int[no_vert];

	pi = new int*[no_vert];
	  for (int i = 0; i < no_vert; i++)
	    pi[i] = new int[no_vert];




	for (int i=0; i < no_vert; i++){
		for (int j=0; j < no_vert; j++){
			d[i][j] = m_edge[i][j];
		}
	}

	for (int i=0; i < no_vert; i++)
		          d[i][i] = 0;

	for (int i = 0; i < no_vert; i++)
	    for (int j = 0; j < no_vert; j++)
	    {
	      if (i != j && m_edge[i][j] < INT_MAX)
	      {
	        pi[i][j] = i;
	      }
	      else if(i==j || m_edge[i][j] == INT_MAX)
	      {
	        pi[i][j] = INT_MAX;
	      }
	 }



	for(int k=0;k<no_vert;k++){
		for(int i=0;i<no_vert;i++){
			if(d[i][k] != INT_MAX)
			{
				for(int j=0;j<no_vert;j++){

					if (d[k][j] != INT_MAX)
					{

						if(d[i][j]>(d[i][k] + d[k][j])){
					 		pi[i][j]= pi[k][j];

					 		d[i][j] = d[i][k] + d[k][j];
						}


					}

				}
			}
		}
	}


}
