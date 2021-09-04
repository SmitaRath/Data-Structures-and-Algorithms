#include <iostream>
#include <set>
#include <queue>

#include "graph.h"

using namespace std;

Graph::Graph(int nodes)
{
    this->nodes = nodes;
    this->matrix = new int*[nodes];

    for (int i = 0; i < nodes; ++i) {
        (this->matrix)[i] = new int[nodes];

        for (int j = 0; j < nodes; ++j) {
            (this->matrix)[i][j] = 0;
        }
    }
}

Graph::Graph(int **matrix, int nodes) {
    this->nodes = nodes;
    this->matrix = new int*[nodes];

    for (int i = 0; i < nodes; ++i) {
        (this->matrix)[i] = new int[nodes];

        for (int j = 0; j < nodes; ++j) {
            (this->matrix)[i][j] = matrix[i][j];
        }
    }
}

Graph::~Graph()
{
    for (int i = 0; i < this->nodes; ++i) {
        delete[] (this->matrix)[i];
    }
    delete[] this->matrix;
}

bool Graph::set_edge(int i, int j, int edge)
{
    if (i < this->nodes && j < this->nodes && i >= 0 && j >= 0) {
        (this->matrix)[i][j] = edge;
        return true;
    }

    return false;
}

void Graph::dfs()
{
	int start=0;
	set<int>* visited = new set<int>;
	for(int i=0;i<nodes;i++){
	    if(visited->find(i)==visited->end())
	    	dfs(i,visited);
	}
	delete visited;
}

void Graph::dfs(int start, set<int> *visited)
{
	if(visited->find(start)==visited->end()){
		cout<<start<<" ";
		visited->insert(start);

		for(int i=0;i<nodes;i++){
			if(this->matrix[start][i]==1 && visited->find(i)==visited->end()){
				dfs(i,visited);
			}
		}
	}
}

void Graph::bfs(int start)
{
	/*for (int i = 0; i < nodes; ++i) {
		for (int j = 0; j < nodes; ++j) {
	        cout<< (this->matrix)[i][j]<<" ";
	      }
	        cout<<endl;
	    }*/

	set<int>* visited=new set<int>;

	bfs(start,visited);
	delete visited;
}

void Graph::bfs(int start, set<int> *visited)
{
	queue<int> explored;
	visited->insert(start);
	cout<<start<<" ";
	explored.push(start);
	int u;
	while(explored.size()!=0){
		u=explored.front();
		explored.pop();
		for(int i=0;i<nodes;i++){
			if(this->matrix[u][i]!=0 && visited->find(i)==visited->end()){
				cout<<i<<" ";
				visited->insert(i);
				explored.push(i);
			}
		}
	}
}



int t;

/*void Graph::dfsend(int start)
{
	t=0;
	int* n = new int[nodes];
	int* s = new int[nodes];
	int* f = new int[nodes];

	set<int>* visited = new set<int>;
	dfsend(start,visited,s,f);
    for(int i=nodes-1;i>=1;i--){
    	if(visited->find(i)==visited->end())
    		dfsend(i,visited,s,f);
    }

    cout<< endl;
    for(int i=0;i<nodes;i++){
    cout<<i<<" "<<s[i]<<" "<<f[i]<<endl;
    }


	delete n,s,f;
}

void Graph::dfsend(int start, set<int> *visited, int s[], int f[])
{
	t=t+1;

		cout<<start<<" ";
		visited->insert(start);
		s[start]=t;

		for(int i=nodes-1;i>=1;i--){
			if(this->matrix[start][i]==1 && visited->find(i)==visited->end()){
				dfsend(i,visited,s,f);

			}
		}
		t=t+1;
		f[start]=t;
	}

void Graph::dfsstart(int start)
{
	int* n = new int[nodes];
	int* s = new int[nodes];
	int* f = new int[nodes];

	set<int>* visited = new set<int>;
	dfsstart(start,visited,s,f);
    for(int i=1;i<nodes;i++){
    	if(visited->find(i)==visited->end())
    		dfsstart(i,visited,s,f);
    }

    cout<< endl;
    for(int i=0;i<nodes;i++){
    cout<<i<<" "<<s[i]<<" "<<f[i]<<endl;
    }


	delete n,s,f;
}

void Graph::dfsstart(int start, set<int> *visited, int s[], int f[])
{
	t=t+1;

		cout<<start<<" ";
		visited->insert(start);
		s[start]=t;

		for(int i=1;i<nodes;i++){
			if(this->matrix[start][i]==1 && visited->find(i)==visited->end()){
				dfsstart(i,visited,s,f);

			}
		}
		t=t+1;
		f[start]=t;
	}*/


