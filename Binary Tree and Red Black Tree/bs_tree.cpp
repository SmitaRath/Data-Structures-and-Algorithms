
#include "bs_tree.h"
#include <list>
#include <iostream>
#include <vector>
using namespace std;

/*
 * constructor/destructor
 */
bs_tree::bs_tree()
{ 
	  T_nil = new bs_tree_node();
	  T_nil->p = T_nil;
	  T_nil->left = T_nil;
	  T_nil->right = T_nil;
	  T_root = T_nil;
} 

bs_tree::~bs_tree()
{ 
	remove_all(T_root);
    delete T_nil;
} 


void bs_tree::insert(int key, bs_tree_i_info& t_info)
{ 
	  bs_tree_node* z;

	  z = new bs_tree_node;
	  z->key = key;

	  insert(z, t_info);
}

//method for insertion
void bs_tree::insert(bs_tree_node* z, bs_tree_i_info& t_info)
{ /*<<<*/
/*
 * binary tree type insert
 * -> search position, insert new node
 * -> fix properties after insert
 */
	bs_tree_node* x;
	bs_tree_node* y;

  y = T_nil;
  x = T_root;
  while (x != T_nil)
    {
      y = x;

      if (z->key < x->key)
	x = x->left;
      else if (z -> key > x->key)
    	 x = x->right;
      else{
    	  //checking for duplicates and updating the counter
    	  t_info.i_duplicate=t_info.i_duplicate+1;
    	  return;
      }
    }

  z->p = y;
  if (y == T_nil)
    T_root = z;
  else
    {
      if (z->key < y->key)
	y->left = z;
      else
	y->right = z;
    }

  z->left = T_nil;
  z->right = T_nil;

}

// TODO: modified inorder tree walk method to save the 
// sorted numbers in the first argument: int* array.
// question 2
int bs_tree::convert(int* array, int n)
{
	vector<int> newArray;

	 if (T_root != T_nil)
	    {
		 convertSort(T_root,newArray);
	    }

	 //copying elements from vector to array
	 n=newArray.size();
	 for(int i=0;i<n;i++){
		 array[i]=newArray.at(i);
	 }
	 newArray.clear();
  return n;
}

//declared new method for traversing and pusing into array.
void bs_tree::convertSort(bs_tree_node* x, vector<int> &newArray)
{
  if (x != T_nil)
    {
	  convertSort(x->left,newArray);
	  newArray.push_back(x->key);
      convertSort(x->right,newArray);
    }
}


//method to delete the binary tree.
void bs_tree::remove_all(bs_tree_node* x)
{ /*<<<*/
/*
 * recursively remove all tree elements
 */
  if (x != T_nil)
    {
      remove_all(x->left);
      remove_all(x->right);

      delete x;
    }
}


