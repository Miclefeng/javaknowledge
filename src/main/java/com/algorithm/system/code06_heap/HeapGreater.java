package com.algorithm.system.code06_heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/2 07:32
 */
public class HeapGreater<T> {

    public ArrayList<T> heap;
    public int size;
    public HashMap<T, Integer> indexMap;
    public Comparator<? super T> comp;

    public HeapGreater(Comparator<? super T> comp) {
        this.heap = new ArrayList<>();
        this.size = 0;
        this.indexMap = new HashMap<>();
        this.comp = comp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public boolean contains(T v) {
        return indexMap.containsKey(v);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T v) {
        heap.add(v);
        indexMap.put(v, size);
        heapInsert(size++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, size - 1);
        indexMap.remove(ans);
        heap.remove(--size);
        heapify(0);
        return ans;
    }

    public void remove(T v) {
        T replace = heap.get(size - 1);
        int index = indexMap.get(v);
        indexMap.remove(v);
        heap.remove(--size);
        if (replace != v) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            ressign(index);
        }
    }

    public List<T> getAllElements() {
        return new ArrayList<>(heap);
    }

    public void ressign(int index) {
        heapInsert(index);
        heapify(index);
    }

    public void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int index) {
        int left = (index << 1) + 1;
        while (left < size) {
            int best = left + 1 < size && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            best = comp.compare(heap.get(index), heap.get(best)) < 0 ? index : best;
            if (best == index) {
                break;
            }
            swap(index, best);
            index = best;
            left = (index << 1) + 1;
        }
    }

    public void swap(int a, int b) {
        T o1 = heap.get(a);
        T o2 = heap.get(b);
        heap.set(a, o2);
        heap.set(b, o1);
        indexMap.put(o1, b);
        indexMap.put(o2, a);
    }

    public static void main(String[] args) {

    }
}
