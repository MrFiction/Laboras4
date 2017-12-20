package lab4;

import java.util.Arrays;
import laborai.studijosktu.*;

/**
 *
 * @author Belkas
 */
public class MapKTUOA<K, V> implements MapADT <K, V> {

    protected Node<K, V>[] table;

    
    protected int size = 0;
    protected int index = 0;
    

    public MapKTUOA(int size) {
        table = new Node[size];
    }
    
    @Override
    public boolean isEmpty() {
        
        return size == 0;
        
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(table,null);
        size = 0;
    }

    @Override
    public String[][] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public V put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key is null in put(Key key)");
            if(size >= table.length * 0.75)  // vengiame nesibaigiančio ciklo ir geriname efektyvumą
                rehash();
            index = hash(key);
            int hashAdded = hash2(key);
            while (table[index] != null)
            {
                index += hashAdded;
                index %= table.length;
            }
                table[index] = new Node(key, value);
                size++;
        
        return value;
    }

    @Override
    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key is null in get(Key key)");
        index = hash(key);
        return table[findNode(key, index)].value;
    }

    public V get (int index){
        if(table[index] == null)
            return null;
        return table[index].value;
    }
    
    
    @Override
    public V remove(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key is null in remove(Key key)");

        index = findNode(key, hash(key));
        if(index != -1) {
            table[index].empty = true;
            size--;
            return table[findNode(key, index)].value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return findNode(key, hash(key)) != -1;
    }
    
    
    
    private int findNode(K key, int startIndex) {
            if(size == table.length)
                rehash(); // išvengiama nesibaigiančio ciklo
            int hash2 = hash2(key);
            while(table[startIndex] != null && !table[startIndex].key.equals(key)){
                startIndex += hash2;
                startIndex %= table.length;
            }
            return startIndex;
            
    }
    
    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }
    
    private int hash2(K key){
        return Math.abs((key.hashCode() % 7)) + 1;
    }
    

    
    
    private void rehash() {
        MapKTUOA mapKTU
                = new MapKTUOA(table.length * 2);
        for (int i = 0; i < table.length; i++) {
            if(table[i] != null && !table[i].empty)
                mapKTU.put(table[i].key, table[i].value);
        }
        table = mapKTU.table;
        
    }


    protected class Node<K ,V> {
        
        
        protected K key;
        protected V value;
        private boolean empty;
        
        protected Node(){ 
            empty = true;
        }
        
        protected Node(K key,V value) {
            this.key = key;
            this.value = value;
            empty = false;
        }
        
        @Override
        public String toString() {
            return key + "=" + value;
        }
        
    }
}
