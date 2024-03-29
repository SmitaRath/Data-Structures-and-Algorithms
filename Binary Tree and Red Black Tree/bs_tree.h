#ifndef __BS_TREE_H__
#define __BS_TREE_H__
#include <vector>
struct bs_tree_node 
{
  int		key;
  bs_tree_node*	left;
  bs_tree_node*	right;
  bs_tree_node*	p;
};

struct bs_tree_i_info
{
  int i_duplicate;

  void reset()
    { i_duplicate = 0; }
};


class bs_tree
{ 
  protected:
    bs_tree_node*	T_nil;
    bs_tree_node*	T_root;

  public:
    bs_tree();
    ~bs_tree();

    void insert(int, bs_tree_i_info&);
    int convert(int*, int);
    void remove_all(bs_tree_node*);
    void insert(bs_tree_node*, bs_tree_i_info&);
    void convertSort(bs_tree_node*,std::vector<int> &vect);
   
};


#endif
