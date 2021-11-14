// In an interview you can generally omit the import statements,
// however if you decide to use some library it's a good idea to
// mention it to the Interviewer and ask if it's allowed.

import java.util.ArrayList;
import java.util.Objects;

// A node of chains
class HashNode<K, V> {
    K key;
    V value;
      final int hashCode;
 
    // Reference to next node
    HashNode<K, V> next;
 
    // Constructor
    public HashNode(K key, V value, int hashCode)
    {
        this.key = key;
        this.value = value;
          this.hashCode = hashCode;
    }
}

public class HashTable<K, V> {
	
	// bucketArray store array of chains
    private ArrayList<HashNode<K, V> > bucketArray;
 
    // Current size of array list
    private int size;
    
    // Current capacity of array list
    public int numBuckets;
    
    //Maximum allowed load factor= number of entries/number of slots
    public double load_factor;

    // Hash table constructor
    // Arguments:
    //   nslots - number of slots, or 'buckets', the data structure should be
    //            initialized with
    //   max_load - maximum allowed load factor (= num_entries / num_slots);
    //              a positive floating-point number
    
    public HashTable(int numBuckets,double load_factor)
    {
        bucketArray = new ArrayList<>();
        this.load_factor=load_factor;
        this.numBuckets = numBuckets;
        size = 0;
 
        // Create empty chains
        for (int i = 0; i < this.numBuckets; i++)
            bucketArray.add(null);
    }
 
    // Returns current number of elements in the data structure.
    public int size() { return size; }
    
    // Returns current number of slots/buckets used by the implementation.
    public int nslots() {return numBuckets; }
    
    public boolean isEmpty() { return size() == 0; }
     
    private final int hashCode (K key) {
        return Objects.hashCode(key);
    }
   
    // This implements hash function to find index
    // for a key
    private int getBucketIndex(K key)
    {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;
        // key.hashCode() coule be negative.
        index = index < 0 ? index * -1 : index;
        return index;
    }
 
    // If present, removes an existing mapping of the given key to a value and
    // returns the value. Otherwise returns null.
    public V remove(K key)
    {
        // Apply hash function to find index for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        // Get head of chain
        HashNode<K, V> head = bucketArray.get(bucketIndex);
 
        // Search for key in its chain
        HashNode<K, V> prev = null;
        while (head != null) {
            // If Key found
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;
 
            // Else keep moving in chain
            prev = head;
            head = head.next;
        }
 
        // If key was not there
        if (head == null)
            return null;
 
        // Reduce size
        size--;
 
        // Remove key
        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);
 
        return head.value;
    }
 
    // Returns the value associated with the given key, or null if there
    // isn't one.
    public V get(K key)
    {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
          int hashCode = hashCode(key);
       
        HashNode<K, V> head = bucketArray.get(bucketIndex);
 
        // Search key in chain
        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }
 
        // If key not found
        return null;
    }
 
    // Associates given value with the given key. Returns previous value
    // associated with the given key, or null if there wasn't one.
    public K put(K key, V value)
    {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
          int hashCode = hashCode(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
 
        // Check if key is already present
        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return head.key;
            }
            head = head.next;
        }
 
        // Insert key in chain
        size++;
        
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode
            = new HashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);
 
        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0 * size) / numBuckets >= load_factor) {
            ArrayList<HashNode<K, V> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);
 
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
        return null;
    }
}


/*

// Driver method to test Map class
    public static void main(String[] args)
    {
    	int numBuckets=10;
    	double load_factor=0.7;
        HashTable<String, Integer> map = new HashTable<>(numBuckets,load_factor);
        System.out.println( map.put("this", 1));
        map.put("coder", 2);
        System.out.println(map.put("this", 4));
        map.put("hi", 5);
        System.out.println(map.size());
        System.out.println(map.remove("this"));
        System.out.println(map.remove("this"));
        System.out.println(map.size());
        System.out.println(map.nslots());
    }
*/