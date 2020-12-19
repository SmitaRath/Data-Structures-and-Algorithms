
public class IDLListTest {

	public static void main(String[] args) {

		IDLList<Integer> emptylist1 = new IDLList<>();
		IDLList<Integer> oneElementlist = new IDLList<>();
		IDLList<Integer> list = new IDLList<>();
		oneElementlist.add(50);
		list.add(20);
		list.add(50);
		list.add(36);
		list.add(40);
		list.add(100);
		list.add(69);
		list.add(78);
		list.add(52);
		list.add(10);
		list.add(89);
		list.add(900);

		System.out.println("*********test case1 - when the list is empty. **************/");
		System.out.println("Empty List ==>" + emptylist1.toString());

		// getting the size of the list @SmitaRath
		System.out.println(" Empty List size ==> " + emptylist1.size());

		// getting item from empty list @SmitaRath
		try {
			emptylist1.get(0);
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting head from empty list. @SmitaRath
		try {
			emptylist1.getHead();
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting tail from empty list @SmitaRath
		try {
			emptylist1.getLast();
		} catch (Exception e) {
			System.out.println(e);
		}

		// removing data from empty list @SmitaRath

		try {
			emptylist1.remove();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			emptylist1.remove(12);
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			emptylist1.removeAt(12);
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			emptylist1.removeLast();
		} catch (Exception e) {
			System.out.println(e);
		}

		// adding data to empty list at index @SmitaRath
		try {
			emptylist1.add(2, 25);
		} catch (Exception e) {
			System.out.println(e);
		}

		// adding data to head and deleting from head @SmitaRath
		try {
			System.out.println(emptylist1.add(25));
			System.out.println(emptylist1.remove());
		} catch (Exception e) {
			System.out.println(e);
		}

		// appending data to empty list @SmitaRath
		try {
			System.out.println(emptylist1.append(25));
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(" Empty List size ==> " + emptylist1.size());

		System.out.println("/*********test case2 - when the list has one element. **************/");
		System.out.println("One Element List ==>" + oneElementlist.toString());
		System.out.println(" One Element List size ==> " + oneElementlist.size());

		// getting item from the list which has one element @SmitaRath
		try {
			// should pass
			System.out.println(oneElementlist.get(0));
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting item from the list which has one element @SmitaRath
		try {
			// should fail
			System.out.println(oneElementlist.get(1));
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting head from the list which has one element @SmitaRath
		try {
			System.out.println(oneElementlist.getHead());
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting tail from the list which has one element @SmitaRath
		try {
			System.out.println(oneElementlist.getLast());
		} catch (Exception e) {
			System.out.println(e);
		}

		// removing data from the list which has one element @SmitaRath

		try {
			System.out.println(oneElementlist.remove());
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			oneElementlist.add(0, 50);
			System.out.println(oneElementlist.remove(50));
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			oneElementlist.add(0, 50);
			System.out.println(oneElementlist.removeAt(0));
			System.out.println(oneElementlist.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			oneElementlist.add(0, 50);
			System.out.println(oneElementlist.removeLast());
			System.out.println(oneElementlist.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// adding data to the list which has one element @SmitaRath
		try {
			oneElementlist.add(0, 50);
			// should fail
			System.out.println(oneElementlist.add(2, 25));
			System.out.println(oneElementlist.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			// should pass
			System.out.println(oneElementlist.add(1, 25));
			System.out.println(oneElementlist.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// adding data from the front to the list which has one element @SmitaRath
		try {
			oneElementlist.remove(25);
			System.out.println(oneElementlist.add(25));
			System.out.println(oneElementlist.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// appending data to the list which has one element @SmitaRath
		try {
			oneElementlist.remove(25);
			System.out.println(oneElementlist.append(25));
			System.out.println(oneElementlist.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting head from the list which has one element @SmitaRath
		try {
			System.out.println(oneElementlist.getHead());
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting tail from the list which has one element @SmitaRath
		try {
			System.out.println(oneElementlist.getLast());
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(" One Element List size ==> " + oneElementlist.size());

		System.out.println("/*********test case3 - when the list has more than one element. **************/");

		System.out.println("Multiple element list ==>" + list.toString());
		System.out.println(" Multiple Element List size ==> " + list.size());

		// getting item from the list @SmitaRath
		try {
			// should pass
			System.out.println(list.get(3));
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting item from the list @SmitaRath
		try {
			// should fail
			System.out.println(list.get(12));
		} catch (Exception e) {
			System.out.println(e);
		}

		// removing data from the list @SmitaRath

		try {
			System.out.println(list.remove());
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// removing node which is equal to 89 and index 0 @SmitaRath
		try {
			System.out.println(list.remove(89));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// removing node From tail @SmitaRath
		try {
			System.out.println(list.remove(20));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// removing node from the center. @SmitaRath
		try {
			System.out.println(list.remove(36));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// removing node at index 0 @SmitaRath
		try {
			System.out.println(list.removeAt(0));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// removing node From tail @SmitaRath
		try {
			System.out.println(list.removeAt(5));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// removing node From center @SmitaRath
		try {
			System.out.println(list.removeAt(2));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			System.out.println(list.removeLast());
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting head from the list @SmitaRath
		try {
			System.out.println(list.getHead());
		} catch (Exception e) {
			System.out.println(e);
		}

		// getting tail from the list @SmitaRath
		try {
			System.out.println(list.getLast());
		} catch (Exception e) {
			System.out.println(e);
		}

		// adding data to the list @SmitaRath
		try {
			System.out.println(list.add(1, 25));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			// adding data to the same position @SmitaRath
			System.out.println(list.add(1, 50));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			// adding data at the index equal to size @SmitaRath
			System.out.println(list.add(5, 101));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// adding data from the head @SmitaRath
		try {
			System.out.println(list.add(80));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		// appending data at the end to the list @SmitaRath
		try {
			System.out.println(list.append(75));
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(" Multiple Element List size ==> " + list.size());
	}

}
