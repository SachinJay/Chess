package game;

public enum Status
{
	WHITE_WIN,
	BLACK_WIN,
	STALEMATE,
	FORFEIT,
	IN_PLAY,
	PAUSED,
	BLACK_IS_IN_CHECK, 
	WHITE_IS_IN_CHECK;
	
	/**
	 * Makes status into string
	 * Could not override toString() to implement because this function calls toString()
	 * @return string version of status, instead of underscores, spaces, all lowercase except first letter of
	 * each word
	 */
	public String makeString()
	{
		String str = this.toString().replaceAll("_", " ");
		
		str = ((Character)str.charAt(0)).toString() + str.substring(1).toLowerCase();
		
		return str.trim();
	}
}
