package checkers;

import java.util.ArrayList;
import java.util.List;


/**
 * REUSING DSA CODE FOR SORTING
 * @author jayact
 *
 * @param <E>
 */
public class QuickSort <E extends Comparable>
{
	List<E> list;
	static int i = 0;
        

	public void sort(List<E> list) 
	{
		this.list = list;
		qsort(0, list.size()-1);
	}
	private void qsort(int start, int end)
	{
		if(start >= end)
			return;
		int p = partition(start, end);
		qsort(start, p-1);
		qsort(p+1, end);
	}
	private int partition(int start, int end)
	{
		int p = (start + end)/2;
		E pivot = list.get(p);
		list.set(p, list.get(start));
		list.set(start, pivot);
		p = start;
		for(int i = start; i <= end; i++)
		{
			if(list.get(i).compareTo(pivot) < 0)
			{
				list.set(p,list.get(i));
				p++;
				list.set(i, list.get(p));
			}			
		}
		list.set(p, pivot);
		return p;
	}
	public List<Integer> createList(List<Integer> a, int start, int end)
	{
		if(start >= end)
			return a;
		int p = (start+end)/2;
		a.set(p, i);
		i++;
		a = createList(a, start, p -1);
		a = createList(a, p+1, end);
		return a;
	}
}
