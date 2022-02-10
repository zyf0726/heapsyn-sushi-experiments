package forSushi.sushi.dll;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class DllLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/sushi/dll/LinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/sushi/dll/LinkedList.*");
		super.configureScope(p, "forSushi/sushi/dll/LinkedList$Entry");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/sushi/dll.jbse"));
	}
	
	public static void main(String[] args) {
		DllLauncher launcher = new DllLauncher();
		launcher.startSushi();
	}

}
