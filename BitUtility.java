
package ceng.ceng351.labdb;


public class BitUtility 
{

	
	public static int getRightMostBits(int value, int n)
	{
		if (n == 0) {
			return 0;
		}
		else {
			return value & ((1<<n)-1);
		}
	}

	public static boolean endsWith0(int value)
	{
		if ((value & 0x00000001) == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean endsWith1(int value)
	{
		return !BitUtility.endsWith0(value);
	}
}
