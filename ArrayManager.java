package checkers;

import java.lang.reflect.Array;

public class ArrayManager 
{
	/**
	 * just a helper function, combines two arrays (jump and move array)
	 * @param a is the first array
	 * @param b is the second array
	 * @return the combined arrays.
	 * @throws Exception 
	 */
	public static <T> T[] combineArrays(T[] a, T[] b, Class<T> type)
	{
		T[] p = (T[]) Array.newInstance(type, a.length + b.length);
		for(int j = 0; j < a.length; j++)
		{
			p[j] = a[j];
		}
		for(int j = 0; j < b.length; j++)
		{
			p[j+a.length] = b[j];
		}
		return p;
	}
	/**
	 * just a helper function, resizes an array to the item size
	 * @param a is the array.
	 * @return the resized array.
	*/
	  public static <T> T[] resizeArray(T[] a, Class<T> type) 
	  {
		int a_count = 0;
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] != null)
				a_count++;
		}
		T[] result = (T[]) Array.newInstance(type, a_count);
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] != null)
			{
				result[--a_count] = a[i];
			}
		}
		return result;
	}
}
