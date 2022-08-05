package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node nodemap;
    private int size;
    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
    public BSTMap(){
        nodemap = null;
        size = 0;
    }

    @Override
    public void clear() {
        size = 0;
        nodemap = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(nodemap,key);
    }
    private boolean containsKey(Node n, K key){
        if(n == null){
            return false;
        }
        if(n.key.compareTo(key) > 0){
            return containsKey(n.left,key);
        }else if(n.key.compareTo(key) < 0){
            return containsKey(n.right,key);
        }else{
            return true;
        }
    }

    @Override
    public V get(K key) {
        return get(nodemap, key);
    }
    private V get(Node n, K key){
        if(!containsKey(n, key)){
            return null;
        }
        if(n.key.compareTo(key) > 0){
            return get(n.left,key);
        }
        if(n.key.compareTo(key) == 0){
            return n.value;
        }
        if(n.key.compareTo(key) < 0){
            return get(n.right,key);
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(!containsKey(key)){
            size ++;
        }
        nodemap = put(nodemap,key,value);
    }
    private Node put(Node n,K key, V value){
        if(n == null){
            n =new Node(key,value);
        }
        if(n.key == key){
            n.value = value;
        }
        if(n.key.compareTo(key) > 0){
            n.left = put(n.left, key, value);
        }
        if(n.key.compareTo(key) < 0) {
            n.right = put(n.right, key, value);
        }
        return n;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
