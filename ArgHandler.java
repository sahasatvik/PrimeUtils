
public class ArgHandler {

	private boolean[] flags;
	private String[][] flagTable;

	private String[] arguments;
	private String[] rawArgs;
	private int minArgs;
	
	private boolean tooFewArgs;
	private boolean flagNotFound;
	private String unknownFlags;

	public ArgHandler (String[] args, String[][] flagTable, int minArgs) {
		
		flags = new boolean[256];
		rawArgs = new String[args.length];

		this.flagTable = new String[flagTable.length][2];
		for (int i = 0; i < flagTable.length; i++) {
			for (int j = 0; j < 2; j++) {
				this.flagTable[i][j] = flagTable[i][j];
			}
		}
		for (int i = 0; i < args.length; i++) {
			rawArgs[i] = args[i];
		}
		for (int i = 0; i < 256; i++) {
			flags[i] = false;
		}
		arguments = new String[0];
		flagNotFound = false;
		unknownFlags = "";

		processflags(args);
		
		tooFewArgs = (arguments.length < minArgs);
	}

	private void processflags (String[] args) {
		for (String arg : args) {
			if (processflags(arg)) {
				pushArg(arg);
			}
		}
	}
	private boolean processflags (String arg) {
		if (arg.charAt(0) == '-') {
			arg = arg.substring(0, (arg.indexOf("=") == -1)? arg.length() : arg.indexOf("="));
			if (arg.charAt(1) == '-') {
				if (isInTable(arg)) {
					char opt = getShortOpt(arg);
					flags[opt] = true;
				} else {
					unknownFlags += " " + arg;
					flagNotFound = true;
				}
			} else {
				for (int i = 1; i < arg.length(); i++) {
					if (isInTable(("-" + arg.charAt(i)))) {
						flags[(int)arg.charAt(i)] = true;
					} else {
						unknownFlags += " -" + arg.charAt(i);
						flagNotFound = true;
					}
				}
			}
			return false;
		}
		return true;
	}
	public boolean checkFlag (char c) {
		return flags[(int)c];
	}
	public boolean checkFlag (String s) {
		return checkFlag(getShortOpt(s));
	}
	public boolean hasTooFewArgs () {
		return tooFewArgs;
	}
	public boolean hasUnknownFlag () {
		return flagNotFound;
	}
	public String getUnknownFlags () {
		return unknownFlags;
	}
	public boolean hasMoreArgs () {
		return (arguments.length > 0);
	}
	public boolean hasArg (String arg) {
		for (String a : arguments) {
			if (a.equals(arg)) {
				return true;
			}
		}
		return false;
	}
	public String next () {
		return popArg (0);
	}
	public int nextInt () {
		return nextInt(0);
	}
	public double nextDouble () {
		return nextDouble(0);
	}
	public int nextInt (int i) {
		int x = 0;
		while (i < arguments.length) {
			try {
				x = Integer.parseInt(arguments[i]);
				popArg(i);
				return x;
			} catch (NumberFormatException e) {
				i++;
			}
		}
		return Integer.MIN_VALUE;
	}
	public double nextDouble (int i) {
		double x = 0;
		while (i < arguments.length) {
			try {
				x = Double.parseDouble(arguments[i]);
				popArg(i);
				return x;
			} catch (NumberFormatException e) {
				i++;
			}
		}
		return Double.MIN_VALUE;
	}
	public String valueOfFlag (String s) {
		return valueOfFlag(getShortOpt(s));
	}
	public String valueOfFlag (char c) {
		if (checkFlag(c)) {
			int rawIndex = rawIndexOfFlag(c);
			String s = rawArgs[rawIndex];
			s = s.substring(((s.indexOf("=") == -1)? s.length() : (s.indexOf("=") + 1)), s.length());
			return s;
		}
		return "";
	}
	private int indexOfArg (String s) {
		int i = -1;
		if (hasArg(s)) {
			while (!arguments[++i].equals(s));
		}
		return i;
	}
	private int rawIndexOfFlag (char c) {
		for (int i = 0; i < rawArgs.length; i++) {
			String s = rawArgs[i];
			s = s.substring(0, (s.indexOf("=") == -1)? s.length() : s.indexOf("="));
			if (s.charAt(0) == '-') {
				if ((s.charAt(1) == '-') && (s.equals(getLongOpt(c)))) {
					return i;
				} else {
					for (int j = 1; j < s.length(); j++) {
						if (s.charAt(j) == c) {
							return i;
						}
					}
				}
			}
		}
		return -1;
	}
	private void pushArg (String arg) {
		String[] t = new String[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			t[i] = arguments[i];
		}
		arguments = new String[t.length + 1];
		for (int i = 0; i < t.length; i++) {
			arguments[i] = t[i];
		}
		arguments[t.length] = arg;
	}
	private String popArg (int n) {
		if ((arguments.length > 0) && (n >= 0) && (n < arguments.length)) {
			String arg = arguments[n];
			String[] t = new String[arguments.length];
			for (int i = 0; i < arguments.length; i++) {
				t[i] = arguments[i];
			}
			arguments = new String[t.length - 1];
			for (int i = 0; i < n; i++) {
				arguments[i] = t[i];
			}
			for (int i = n; i < arguments.length; i++) {
				arguments[i] = t[i+1];
			}
			return arg;
		} 
		return "";
	}
	private boolean isInTable (String s) {
		for (String line[] : flagTable) {
			for (String option : line) {
				if (s.equals(option)) {
					return true;
				}
			}
		}
		return false;
	}
	private char getShortOpt (String s) {
		for (String line[] : flagTable) {
			if (line[1].equals(s)) {
				return line[0].charAt(1);
			}
		}
		return '-';
	}
	private String getLongOpt (char c) {
		for (String line[] : flagTable) {
			if (line[0].charAt(1) == c) {
				return line[1];
			}
		}
		return "";
	}
} 
