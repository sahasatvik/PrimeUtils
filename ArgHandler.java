
public class ArgHandler {

	private boolean[] switches;
	private String[][] switchTable;

	private String[] arguments;
	private String[] rawArgs;
	private int minArgs;
	
	private boolean tooFewArgs;
	private boolean optionNotFound;
	private String unknownOptions;

	public ArgHandler (String[] args, String[][] switchTable, int minArgs) {
		
		switches = new boolean[256];
		rawArgs = new String[args.length];

		this.switchTable = new String[switchTable.length][2];
		for (int i = 0; i < switchTable.length; i++) {
			for (int j = 0; j < 2; j++) {
				this.switchTable[i][j] = switchTable[i][j];
			}
		}
		for (int i = 0; i < args.length; i++) {
			rawArgs[i] = args[i];
		}
		for (int i = 0; i < 256; i++) {
			switches[i] = false;
		}
		arguments = new String[0];
		optionNotFound = false;
		unknownOptions = "";

		processSwitches(args);
		
		tooFewArgs = (arguments.length < minArgs);
	}

	private void processSwitches (String[] args) {
		for (String arg : args) {
			if (processSwitches(arg)) {
				pushArg(arg);
			}
		}
	}
	private boolean processSwitches (String arg) {
		if (arg.charAt(0) == '-') {
			if (arg.charAt(1) == '-') {
				if (isInTable(arg)) {
					char opt = getShortOpt(arg);
					switches[opt] = true;
				} else {
					unknownOptions += " " + arg;
					optionNotFound = true;
				}
			} else {
				for (int i = 1; i < arg.length(); i++) {
					if (isInTable(("-" + arg.charAt(i)))) {
						switches[(int)arg.charAt(i)] = true;
					} else {
						unknownOptions += " -" + arg.charAt(i);
						optionNotFound = true;
					}
				}
			}
			return false;
		}
		return true;
	}
	public boolean isOn (char c) {
		return switches[(int)c];
	}
	public boolean isOn (String s) {
		return isOn(getShortOpt(s));
	}
	public boolean hasTooFewArgs () {
		return tooFewArgs;
	}
	public boolean optionNotFound () {
		return optionNotFound;
	}
	public String getUnknownOptions () {
		return unknownOptions;
	}
	public boolean hasArg (String arg) {
		for (String a : arguments) {
			if (a.equals(arg)) {
				return true;
			}
		}
		return false;
	}
	public void pushArg (String arg) {
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
	public String popArg () {
		if (arguments.length > 0) {
			String arg = arguments[0];
			String[] t = new String[arguments.length];
			for (int i = 0; i < arguments.length; i++) {
				t[i] = arguments[i];
			}
			arguments = new String[t.length - 1];
			for (int i = 1; i < t.length; i++) {
				arguments[i - 1] = t[i];
			}
			return arg;
		} 
		return "";
	}
	public boolean isInTable (String s) {
		for (String line[] : switchTable) {
			for (String option : line) {
				if (s.equals(option)) {
					return true;
				}
			}
		}
		return false;
	}
	public char getShortOpt (String s) {
		for (String line[] : switchTable) {
			if (line[1].equals(s)) {
				return line[0].charAt(1);
			}
		}
		return '-';
	}
	public String getLongOpt (char c) {
		for (String line[] : switchTable) {
			if (line[0].charAt(1) == c) {
				return line[1];
			}
		}
		return "";
	}
} 
