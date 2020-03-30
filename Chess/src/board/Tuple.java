package board;

//Convenience class to store tuples
public class Tuple<A,B>
{
	private A first;
	private B second;
	
	public Tuple(A a, B b)
	{
		setFirst(a);
		setSecond(b);
	}
	/**
	 * @return the first
	 */
	public A getFirst()
	{
		return first;
	}
	/**
	 * @param first the first to set
	 */
	public void setFirst(A first)
	{
		this.first = first;
	}
	/**
	 * @return the second
	 */
	public B getSecond()
	{
		return second;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(B second)
	{
		this.second = second;
	}
}
