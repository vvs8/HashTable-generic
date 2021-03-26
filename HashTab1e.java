import java.util.*;

// A node of chains
class Node {
	int key;
  String keyOrig;
	StudentEntry entry;
	Node next;

	public Node(int key, String keyOrig, StudentEntry entry) {
		this.key = key;
		this.entry = entry;
    this.keyOrig = keyOrig;
	}

  public String toString() {
    return "[Key: "+ key +", " + keyOrig + "] - " + entry.toString();
  }
  
}


public class HashTab1e {
	public ArrayList<Node> Buckets;
	protected int capacity;
	protected int size;
  protected int collisions;
  protected int cap_increases = 0;
  //Adjusted collisions that occur after resize, previous collisions don't count:
  protected int adjusted_collisions = 0;
  protected int free_buckets = 0;


	public HashTab1e() {
		Buckets = new ArrayList<>();
		capacity = 10;
		size = 0;
    collisions = 0;
		for (int i = 0; i < capacity; i++)
			Buckets.add(null);
	}

	public int size() { 
    return size; 
  }
	
  public boolean isEmpty() {
     return size() == 0; 
  }

  //Free buckets counter
  public int freeBucketsCount() {
    for(Node n: Buckets) {
      if (n == null)
        free_buckets++;
    }
    return free_buckets;
  }

  //Looking for the bucket with the most entries and counting
  public int mostPopulousBucket() {
    int counter = 1;
    int counter2 = 1;
    Node bucket;
    for(Node n: Buckets) {
      counter2 = 1;
      if (n != null) {
        if (n.next != null) {
          while(n.next != null) {
            n = n.next;
            counter2++;
          }
        }
      }
      if (counter2 >= counter) {
        counter = counter2;
        bucket = n;
      }
    }
    return counter; 
  }

  //Polynomial hash
  private int polynomialHash(String str) {
    int p = 33;
    int m = (int)(1e8);
    int power = str.length();
    int hash_val = 0;
    
    for(int i = 0; i < str.length(); i++) {
      hash_val = (hash_val + ((str.charAt(i) -'a' + 1) * ((int) Math.pow(p, power)))) % m;
      power--;
    }
    return Math.abs(hash_val);
  }

  //Second hash function
  private int hashFun(String str) {
    int hash = 0;
    for (int i = 0; i < str.length(); i++) {
      hash = hash*33 + str.charAt(i) - 'a' + 1;
    }
    return Math.abs(hash);
  }

  //Divisor method
	private int getBucketIndex(String key) {
		int hashCode = polynomialHash(key);
    //int hashCode = hashFun(key);
		int index = hashCode % capacity;
		return index;
	}

  //Remove node by key
	public Node remove(String key) {
	  int bucketIndex = getBucketIndex(key);
		Node head = Buckets.get(bucketIndex);

		Node prev = null;
		while (head != null) {
			if (head.key == polynomialHash(key))
				break;
			prev = head;
			head = head.next;
		}
		if (head == null)
			return null;
		size--;

		if (prev != null)
			prev.next = head.next;
		else
			Buckets.set(bucketIndex, head.next);

		return head;
	}

  //Get node by key
	public Node get(String key) {
		int bucketIndex = getBucketIndex(key);
		Node head = Buckets.get(bucketIndex);

		while (head != null) {
			if (head.key == polynomialHash(key))
				return head;
			head = head.next;
		}
		return null;
	}

  //Resize/rehash
  private void reSize() {
		ArrayList<Node> temp = Buckets;
		Buckets = new ArrayList<>();
    adjusted_collisions = 0;
		capacity = 2 * capacity;
		size = 0;
		for (int i = 0; i < capacity; i++)
			Buckets.add(null);

		for (Node n : temp) {
			while (n != null) {
				add(n.keyOrig, n.entry);
				n = n.next;
			}
		}

    cap_increases++;
	}

  //Add node and a key
	public void add(String key, StudentEntry entry) {
		//key = (entry.personalInfo.email + entry.birthYear + entry.major + entry.firstName + entry.lastName + entry.personalInfo.phoneNumber).toLowerCase();

    //key = (entry.birthYear + entry.personalInfo.phoneNumber + entry.major);

    int bucketIndex = getBucketIndex(key);
    //System.out.println(bucketIndex);
		Node head = Buckets.get(bucketIndex);

    if(head != null) {
      collisions++;
      adjusted_collisions++;
    }

    //Overrides entry in the case of similar hashcode
		while (head != null) {
			if (head.key == polynomialHash(key)) {
				head.entry = entry;
        head.keyOrig = key;
				return;
			}
      //System.out.println(head.key);
			head = head.next;
		}

		size++;

		head = Buckets.get(bucketIndex);
		Node newNode = new Node(polynomialHash(key), key, entry);
		newNode.next = head;
		Buckets.set(bucketIndex, newNode);

    if ((1.0 * size) / capacity >= 0.9) {
      reSize();
    }
	}

  //Printer of hash table
  public void printHashTable() {
    for(Node n: Buckets){
      System.out.println(n);
      if (n != null) {
        if (n.next != null) {
          while(n.next != null) {
            n = n.next;
            System.out.println("   L-->" + n);
          }
        }
      }
    }
  }

  //Printer of info
  public void printCollisions() {
    System.out.println("Size: " + size);
    System.out.println("Collisions: " + collisions);
    System.out.println("Adjusted collisions (after resize): " + adjusted_collisions);
    System.out.println("Capacity: " + capacity);
    System.out.println("Capacity increases: " + cap_increases);
    freeBucketsCount();
    System.out.println("Free buckets: " + free_buckets);


  }
	
}

