package game;

public enum Status
{
	WHITE_WIN,
	BLACK_WIN,
	STALE_MATE,
	CHECK,
	FORFEIT,
	IN_PLAY,
	PAUSED;
	
	/**
	 * Makes status into string
	 * Could not override toString() to implement because this function calls toString()
	 * @return string version of status, instead of underscores, spaces, all lowercase except first letter of
	 * each word
	 */
	public String makeString()
	{
		String[] arr = this.toString().split("_");
		String ret = "";
		for(String str : arr)
		{
			String first = ((Character) str.charAt(0)).toString();
			String last = str.substring(1);
			ret  = ret + first+last.toLowerCase() + " ";
		}
		
		return ret.trim();
	}
}
